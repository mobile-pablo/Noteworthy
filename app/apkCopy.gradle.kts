import org.gradle.api.tasks.Copy

val copyApk by tasks.registering(Copy::class) {
    from("build/outputs/apk/androidTest/debug")
    into("jenkins-build")
    include("*.apk")
}

val copyUIApk by tasks.registering(Copy::class) {
    from("build/outputs/apk/debug")
    into("jenkins-build")
    include("*.apk")
}
