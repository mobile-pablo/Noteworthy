apply(from = "../ktlint.gradle.kts")

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.mobile.pablo.storage"
    compileSdk = 33

    defaultConfig {
        minSdk = 28
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "${projectDir}/schemas"
                arguments["room.incremental"] = "true"
                arguments["room.expandProjection"] = "true"
            }
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
    }
}

tasks.getByPath("preBuild").dependsOn("ktlint")

dependencies {
    api(project(":core"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    api(libs.room.ktx)
    kapt(libs.room.compiler)
}
