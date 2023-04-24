import org.gradle.api.tasks.Copy

val copyApk by tasks.register<JavaExec>("copyApk") {
    mainClass.set("CopyApkTask")
    classpath = files()
    args("build/outputs/apk/androidTest/debug", "jenkins-build", "*.apk")
}

val copyUIApk by tasks.register<JavaExec>("copyUIApk") {
    mainClass.set("CopyApkTask")
    classpath = files()
    args("build/outputs/apk/debug", "jenkins-build", "*.apk")
}

tasks.getByName("build").finalizedBy(copyApk, copyUIApk)
