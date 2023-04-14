apply(from = "../ktlint.gradle.kts")

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp") version "1.8.0-1.0.9"
}

kotlin {
    sourceSets {
        debug {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }

        getByName("release") {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}

android {
    namespace = "com.mobile.pablo.iosnotes"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.mobile.pablo.iosnotes"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile ("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":features:note"))
    implementation(project(":features:addNote"))
    implementation(project(":features:editNote"))
    implementation(project(":uicomponents:common"))

    implementation (libs.androidX.core)
    implementation (libs.androidX.lifecycle)

    implementation (libs.compose.activity)
    implementation (libs.compose.ui)
    implementation (libs.compose.constraint)
    implementation (libs.compose.swipe)
    implementation (libs.compose.tooling.preview)
    implementation (libs.compose.material)
    implementation (libs.compose.coil)
    implementation (libs.compose.lifecycle)
    implementation (libs.compose.viewmodel)
    implementation (libs.compose.hiltNavigation)

    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)

    testImplementation (libs.junit)
    androidTestImplementation (libs.junit.ext)
    androidTestImplementation (libs.espresso.core)
    androidTestImplementation  (libs.uiautomator)
    androidTestImplementation (libs.compose.junit)
    debugImplementation (libs.compose.tooling)
    debugImplementation (libs.compose.testManifest)

    implementation (libs.compose.destination)
    ksp (libs.compose.destination.ksp)
}