plugins {
    id(GradlePlugins.androidLib)
    kotlin(GradlePlugins.kotlinAndroid)
    kotlin(GradlePlugins.kotlinApt)
    kotlin(GradlePlugins.kotlinExt)
}

apply {
    from("../ktlint.gradle")
}

android {
    compileSdkVersion(Android.targetSdk)

    defaultConfig {
        minSdkVersion(Android.minSdk)
        targetSdkVersion(Android.targetSdk)

        testInstrumentationRunner = AndroidJUnit.runner
    }

    buildTypes {
        getByName(BuildType.release) {
            isMinifyEnabled = BuildType.minifyRelease
            proguardFiles(BuildType.proguardRelease)
        }

        getByName(BuildType.debug) {
            isMinifyEnabled = BuildType.minifyDebug
            proguardFiles(BuildType.proguardDebug)
            isTestCoverageEnabled = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // core
    implementation(Libs.stdLib)
    implementation(Libs.ktx)

    // module
    implementation(project(Modules.domain))

    // rx
    implementation(Libs.rxAndroid)
    implementation(Libs.rxJava)

    // room database
    api(Libs.roomRuntime)
    implementation(Libs.roomRxjava2)

    // Lifecycle
    implementation(Libs.lifecycleRuntime)
    implementation(Libs.lifecycleExtensions)
    implementation(Libs.lifecycleJava8)

    // Retrofit
    implementation(Libs.retrofitRuntime)
    implementation(Libs.retrofitGson)
    implementation(Libs.retrofitAdapter)

    // dagger
    implementation(Libs.daggerCore)
    implementation(Libs.daggerAndroid)
    implementation(Libs.daggerSupport)

    kapt(Libs.daggerProcessor)
    kapt(Libs.daggerCompiler)

    implementation(Libs.okLogging)
    implementation(Libs.timber)

    // extension
    kapt(Libs.roomCompiler)

    // Test
    testImplementation(Libs.junit)
    testImplementation(Libs.mockitoCore)
//    testImplementation(Libs.mockitoAll)
    testImplementation(Libs.hamcrest)
    testImplementation(Libs.archTesting)
    testImplementation(Libs.stdLib)
    testImplementation(Libs.kotlinTest)
    testImplementation(Libs.mockitoWebServer)
    testImplementation(Libs.robolectric)
}