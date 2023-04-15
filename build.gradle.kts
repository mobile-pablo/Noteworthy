import org.gradle.kotlin.dsl.`kotlin-dsl`

buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "<https://jitpack.io>")
    }
    dependencies {
        classpath(libs.buildGradlePlugin)
        classpath(libs.kotlinGradlePlugin)
        classpath(libs.hiltPlugin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
