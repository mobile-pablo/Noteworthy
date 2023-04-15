apply(from = "../../ktlint.gradle.kts")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("org.jlleitschuh.gradle.ktlint") version "11.3.1"
    id("org.jlleitschuh.gradle.ktlint-idea") version "11.3.1"
}

android {
    namespace = "com.mobile.pablo.uicomponents.note"
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

dependencies {
    implementation(project(":domain"))
    api(project(":uicomponents:common"))

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
}
