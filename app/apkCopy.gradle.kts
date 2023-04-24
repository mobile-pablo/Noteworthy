import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.JavaExec

val copyUIApk by tasks.register<JavaExec>("copyUIApk") {
    mainClass.set("CopyApkTask")
    classpath = files()
    args("build/outputs/apk/androidTest/debug", "jenkins-build", "*.apk")
}

val copyApk by tasks.register<JavaExec>("copyApk") {
    mainClass.set("CopyApkTask")
    classpath = files()
    args("build/outputs/apk/debug", "jenkins-build", "*.apk")
}

tasks.getByName("build").finalizedBy(copyApk)
tasks.getByName("build").finalizedBy(copyUIApk)
