apply(from = "../../ktlint.gradle.kts")

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.mobile.pablo.uicomponents.common"
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
}

tasks.getByPath("preBuild").dependsOn("ktlint")

dependencies {
    implementation(project(":domain"))

    implementation(libs.compose.ui)
    implementation(libs.compose.constraint)
    implementation(libs.compose.swipe)
    implementation(libs.compose.tooling.preview)
    implementation(libs.compose.material)
    implementation(libs.compose.coil)
    implementation(libs.compose.hiltNavigation)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.google.accompanist)
}
