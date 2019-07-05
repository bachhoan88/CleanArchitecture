plugins {
    id(GradlePlugins.javaLib)
    id(GradlePlugins.kotlin)
}

apply {
    from("../ktlint.gradle")
}

dependencies {
    // kotlin core
    implementation(Libs.stdLib)

    // rx
    implementation(Libs.rxJava)
    implementation(Libs.rxAndroid)

    // dagger
    implementation(Libs.daggerAndroid)
    implementation(Libs.daggerSupport)

    // test
    testImplementation(Libs.koinTest)
    testImplementation(Libs.mockitoCore)
    testImplementation(Libs.junit)
}
