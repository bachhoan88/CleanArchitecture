plugins {
    id(GradlePlugins.android)
    kotlin(GradlePlugins.kotlinAndroid)
    kotlin(GradlePlugins.kotlinApt)
    kotlin(GradlePlugins.kotlinExt)
}

apply {
    plugin(GradlePlugins.navigationSafe)
    from("../ktlint.gradle")
    from("../jacoco.gradle")
}

android {
    compileSdkVersion(Android.targetSdk)
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
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
    implementation(Libs.lifecycleJava8)
    implementation(Libs.lifecycleRuntime)

    // Constraint Layout
    implementation(Libs.constraintlayout)

    // Glide
    implementation(Libs.glideRuntime)

    // Rx
    implementation(Libs.rxJava)
    implementation(Libs.rxAndroid)

    // Binding
//    kapt(Libs.glideCompiler)
    kapt(Libs.lifecycleCompiler)
    kapt(Libs.bindingCompiler)
    kapt(Libs.daggerProcessor)
    kapt(Libs.daggerCompiler)

    // Dagger 2
    implementation(Libs.daggerCore)
    implementation(Libs.daggerAndroid)
    implementation(Libs.daggerSupport)

    implementation(Libs.retrofitGson)
    implementation(Libs.retrofitRuntime)

    // Permission
    implementation(Libs.permission)

    // module
    implementation(project(Modules.domain))
    implementation(project(Modules.data))

    // Navigation
    implementation(Libs.navigationUiKtx)
    implementation(Libs.navigationFragmentKtx)

    // coroutines
    implementation(Libs.coroutinesCore)
    implementation(Libs.coroutinesAndroid)

    // exo player
    implementation(Libs.exoPlayer)

    // Testing
    testImplementation(Libs.junit)
    testImplementation(Libs.archTesting) {
        exclude(group = "com.android.support", module = "support-compat")
        exclude(group = "com.android.support", module = "support-annotations")
        exclude(group = "com.android.support", module = "support-core-utils")
    }

    testImplementation(Libs.mockitoCore)
    testImplementation(Libs.mockitoAll)
}