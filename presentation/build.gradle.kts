plugins {
    id(GradlePlugins.android)
    kotlin(GradlePlugins.kotlinAndroid)
    kotlin(GradlePlugins.kotlinApt)
    kotlin(GradlePlugins.kotlinExt)
}

apply {
    plugin(GradlePlugins.navigationSafeKotlin)
    plugin(GradlePlugins.playService)
    plugin(GradlePlugins.fabric)
    plugin(GradlePlugins.hilt)
    from("../ktlint.gradle")
    from("../googleServices.gradle")
    from("jacoco.gradle")
}

android {
    compileSdkVersion(Android.targetSdk)
    flavorDimensions("default")

    defaultConfig {
        applicationId = Android.applicationId
        minSdkVersion(Android.minSdk)
        targetSdkVersion(Android.targetSdk)
        versionCode = Android.versionCode
        versionName = Android.versionName

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
            isDebuggable = true
        }
    }

    productFlavors {
        create("develop") {
            setMatchingFallbacks(listOf("debug", "qa"))
        }

        create("production") {
            setMatchingFallbacks(listOf("release"))
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    dataBinding {
        isEnabled = true
    }

    androidExtensions {
        isExperimental = true
    }
}

dependencies {
    // ktx core
    implementation(Libs.ktx)
    implementation(Libs.stdLib)

    // support
    implementation(Libs.supportAppCompat)
    implementation(Libs.supportAnnotations)
    implementation(Libs.supportCardview)
    implementation(Libs.supportDesign)
    implementation(Libs.supportRecyclerview)
    implementation(Libs.supportRecyclerviewSelection)
    implementation(Libs.supportLegacyV4)

    // lifecycle
    implementation(Libs.lifecycleExtensions)
    implementation(Libs.lifecycleLiveDataKtx)
    implementation(Libs.lifecycleViewModel)
    implementation(Libs.lifecycleSavedState)
    implementation(Libs.fragmentKtx)

    // Constraint Layout
    implementation(Libs.constraintlayout)

    // Glide
    implementation(Libs.glideRuntime)

    // Rx
    implementation(Libs.rxJava)
    implementation(Libs.rxAndroid)

    // Hilt
    implementation(Libs.hilt)
    kapt(Libs.hiltCompiler)

    implementation(Libs.retrofitGson)
    implementation(Libs.retrofitRuntime)

    // Permission
    implementation(Libs.permission)

    // module
    implementation(project(Modules.domain)) {
        exclude(group = "com.example.cleanarchitecture", module = "domain")
    }
    implementation(project(Modules.data)) {
        exclude(group = "com.example.cleanarchitecture", module = "data")
    }

    // Navigation
    implementation(Libs.navigationUiKtx)
    implementation(Libs.navigationFragmentKtx)

    // coroutines
    implementation(Libs.coroutinesCore)
    implementation(Libs.coroutinesAndroid)

    // logging
    implementation(Libs.timber)

    androidTestImplementation (Libs.mockitoCore) {
        exclude(group = "net.bytebuddy")
    }

    // Dependencies for local unit tests
    testImplementation(Libs.junit)
    testImplementation(Libs.mockitoAll)
    testImplementation(Libs.hamcrest)
    testImplementation(Libs.archTesting)
    testImplementation(Libs.stdLib)
    testImplementation(Libs.kotlinTest)
    testImplementation(Libs.mockitoWebServer)
    testImplementation(Libs.robolectric)

    // android test
    androidTestImplementation(Libs.archTesting)
    androidTestImplementation(Libs.espressoContrib)
    androidTestImplementation(Libs.espressoCore)
    androidTestImplementation(Libs.jUnitExtension)
    androidTestImplementation(Libs.espressoIntents)
    androidTestImplementation(Libs.uiautomator)

    // Firebase analytics
    implementation(Libs.firebaseCore)
    implementation(Libs.firebaseAnalytics)
    implementation(Libs.crashAnalytics) {
        isTransitive = true
    }
}