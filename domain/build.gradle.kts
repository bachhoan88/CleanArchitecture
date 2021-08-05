plugins {
    id(GradlePlugins.javaLib)
    id(GradlePlugins.kotlin)
}

apply {
    from("../ktlint.gradle")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    // kotlin core
    implementation(Libs.stdLib)
    implementation(Libs.ktx)

    // rx
    implementation(Libs.rxJava)
    implementation(Libs.rxAndroid)

    // dagger
//    implementation(Libs.daggerAndroid)
    implementation(Libs.daggerSupport)

    // test
    testImplementation(Libs.junit)
    testImplementation(Libs.mockitoCore)
    testImplementation(Libs.hamcrest)
    testImplementation(Libs.stdLib)
    testImplementation(Libs.kotlinTest)
}
