apply(from = "../../ktlint.gradle.kts")

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") version "1.8.0-1.0.9"
}

android {
    namespace = "com.mobile.pablo.note"
    compileSdk = 33

    defaultConfig {
        minSdk = 28
        targetSdk = 33
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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

    ksp {
        arg(
            "compose-destinations.moduleName",
            "note"
        )
        arg(
            "compose-destinations.mode",
            "destinations"
        )
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":uicomponents:note"))
    implementation(project(":uicomponents:common"))
    implementation(project(":features:editNote"))
    implementation(project(":features:addNote"))

    implementation(libs.androidX.core)
    implementation(libs.androidX.lifecycle)

    implementation(libs.compose.activity)
    implementation(libs.compose.ui)
    implementation(libs.compose.constraint)
    implementation(libs.compose.swipe)
    implementation(libs.compose.tooling.preview)
    implementation(libs.compose.material)
    implementation(libs.compose.coil)
    implementation(libs.compose.lifecycle)
    implementation(libs.compose.viewmodel)
    implementation(libs.compose.hiltNavigation)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.compose.junit)
    debugImplementation(libs.compose.tooling)
    debugImplementation(libs.compose.testManifest)

    implementation(libs.compose.destination)
    ksp(libs.compose.destination.ksp)
}