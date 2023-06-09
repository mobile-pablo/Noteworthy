apply(from = "../ktlint.gradle.kts")

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.mobile.pablo.core"
    compileSdk = 33

    defaultConfig {
        minSdk = 28
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
}

tasks.getByPath("preBuild").dependsOn("ktlint")

dependencies {
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.androidX.lifecycle)
    implementation(libs.androidX.lifecycle.viewmodel)
}
