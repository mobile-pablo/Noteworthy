import com.android.build.api.dsl.ManagedVirtualDevice

apply(from = "../ktlint.gradle.kts")

plugins {
    id("com.android.test")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.mobile.pablo.macrobenchmark"
    compileSdk = 33

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    defaultConfig {
        minSdk = 28
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // Config app for baseline runs
    testOptions {
        managedDevices {
            devices {
                devices.create("pixel2Api31", ManagedVirtualDevice::class) {
                    device = "Pixel 2"
                    apiLevel = 31
                    systemImageSource = "aosp"
                }
            }
        }
    }

    buildTypes {
        // This benchmark buildType is used for benchmarking, and should function like your
        // release build (for example, with minification on). It"s signed with a debug key
        // for easy local/CI testing.
        create("benchmark") {
            isDebuggable = true
            signingConfig = getByName("debug").signingConfig
            matchingFallbacks += listOf("release")
            proguardFiles("benchmark-rules.pro")
        }
    }

    targetProjectPath = ":app"
    experimentalProperties["android.experimental.self-instrumenting"] = true
}

tasks.getByPath("preBuild").dependsOn("ktlint")

dependencies {
    implementation(project(":uicomponents:common"))
    implementation(project(":uicomponents:note"))

    implementation(libs.junit.ext)
    implementation(libs.espresso.core)
    implementation(libs.uiautomator)
    implementation(libs.junit.macroBenchmark)
}

androidComponents {
    beforeVariants(selector().all()) {
        it.enabled = it.buildType == "benchmark"
    }
}
