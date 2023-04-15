val ktlint by configurations.creating

dependencies {
    ktlint(libs.ktLint)
}

tasks.register<JavaExec>("ktlint") {
    group = "verification"
    description = "Check Kotlin code style."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args("--android","--reporter=html,artifact=com.pinterest.ktlint:ktlint-reporter-html:0.48.2,output=${buildDir}/ktlint.html","--reporter=plain","--reporter=sarif", "src/**/*.kt")
}

tasks.named("check") {
    dependsOn(ktlint)
}

tasks.register<JavaExec>("ktlintFormat") {
    group = "formatting"
    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args("--android", "-F", "src/**/*.kt")
}
