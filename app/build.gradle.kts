apply(from = "../ktlint.gradle.kts")
apply(from = "apkCopy.gradle.kts")

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
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

        testInstrumentationRunner = "com.mobile.pablo.iosnotes.runner.CustomTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        create("benchmark") {
            initWith(getByName("release"))
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false
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

tasks.getByPath("preBuild").dependsOn("ktlint")
tasks.getByName("preBuild").dependsOn("copyApk")
tasks.getByName("preBuild").dependsOn("copyUIApk")

dependencies {
    implementation(project(":features:note"))
    androidTestImplementation(project(":domain"))
    implementation(project(":features:addNote"))
    implementation(project(":features:editNote"))
    implementation(project(":features:note"))
    implementation(project(":uicomponents:common"))

    implementation(libs.androidX.core)
    implementation(libs.androidX.lifecycle)

    implementation(libs.compose.activity)
    implementation(libs.compose.ui)
    implementation(libs.compose.material)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.google.truth) {
        exclude(group = "dagger.fastInit")
        exclude(group = "kapt.kotlin.generated")
        exclude(group = "org.checkerframework")
    }
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.espresso.barista.compose)
    androidTestImplementation(libs.espresso.barista) {
        exclude(group = "org.jetbrains.kotlin")
    }
    androidTestImplementation(libs.uiautomator)
    androidTestImplementation(libs.compose.junit)
    androidTestImplementation(libs.hilt.testing)
    androidTestImplementation(libs.compose.hiltNavigation)
    kaptAndroidTest(libs.hilt.android.compiler)
    androidTestAnnotationProcessor(libs.hilt.android.compiler)

    debugImplementation(libs.compose.tooling)
    debugImplementation(libs.compose.testManifest)

    implementation(libs.compose.destination)
    ksp(libs.compose.destination.ksp)

    implementation(libs.baseline.profile)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
}
