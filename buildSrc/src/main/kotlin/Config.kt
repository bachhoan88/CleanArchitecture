object Versions {
    const val kotlin = "1.3.41"
    internal const val androidPlugin = "3.4.1"

    internal const val androidxCore = "1.0.1"
    internal const val archCore = "2.0.1"
    internal const val room = "2.1.0-alpha03"
    internal const val lifecycle = "2.0.0-beta01"
    internal const val lifecycleSaved = "1.0.0-alpha01"
    internal const val support = "1.1.0-alpha06"
    internal const val supportCardView = "1.0.0"
    internal const val supportLegacy = "1.0.0"
    internal const val supportDesign = "1.1.0-alpha07"

    internal const val glide = "4.7.1"

    internal const val koin = "1.0.1"

    internal const val junit = "4.12"
    internal const val espresso = "3.1.0-alpha4"
    internal const val mockito = "2.7.19"
    internal const val mockitoAll = "1.10.19"
    internal const val hamcrest = "1.3"
    internal const val atslRunner = "1.1.0-alpha1"
    internal const val atslRules = "1.1.0-alpha1"
    internal const val mockWebserver = "3.8.1"
    internal const val robolectric = "3.4.2"

    internal const val retrofit = "2.3.0"
    internal const val okLogging = "3.9.0"

    internal const val constraintLaout = "2.0.0-beta2"

    internal const val rxJava = "2.2.2"
    internal const val rxAndroid = "2.1.0"

    internal const val timber = "4.7.1"

    internal const val easyPermission = "3.0.0"
    internal const val navigation = "2.1.0-alpha05"
    internal const val navigationSafe = "2.1.0-alpha05"

    internal const val exo = "2.8.2"
    internal const val coroutines = "1.1.1"

    internal const val dagger = "2.23.2"
    internal const val fragmentKtx = "1.1.0"
    /**
    versions.multi_dex = '1.0.3'
    versions.apache_commons = "2.5"
    versions.dexmaker = "2.2.0"
    versions.paging = "1.0.0"
    versions.work = "1.0.0-alpha02"
    versions.navigation = "1.0.0-alpha01"
    versions.easy_permission = "1.3.1"
     */
}

object BuildPlugins {
    const val androidPlugin = "com.android.tools.build:gradle:${Versions.androidPlugin}"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigationSafe = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationSafe}"
}

object Android {
    const val minSdk = 21
    const val targetSdk = 28
    const val applicationId = "com.example.cleanarchitecture"
    const val versionCode = 1
    const val versionName = "1.0.1"
}

object GradlePlugins {
    const val android = "com.android.application"
    const val kotlin = "kotlin"
    const val kotlinAndroid = "android"
    const val kotlinExt = "android.extensions"
    const val kotlinApt = "kapt"
    const val javaLib = "java-library"
    const val androidLib = "com.android.library"
    const val navigationSafe = "androidx.navigation.safeargs"
    const val navigationSafeKotlin = "androidx.navigation.safeargs.kotlin"
}

object Modules {
    const val domain = ":domain"
    const val data = ":data"
}

object AndroidJUnit {
    const val runner = "androidx.test.runner.AndroidJUnitRunner"
}

object BuildType {
    const val debug = "debug"
    const val release = "release"

    const val minifyRelease = false
    const val proguardRelease = "proguard-rules.pro"

    const val minifyDebug = false
    const val proguardDebug = "proguard-rules.pro"
}

object Libs {
    //KTX
    const val ktx = "androidx.core:core-ktx:${Versions.androidxCore}"
    const val archTesting = "androidx.arch.core:core-testing:${Versions.archCore}"
    const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"

    // Support libs
    const val supportAnnotations = "androidx.annotation:annotation:${Versions.support}"
    const val supportAppCompat = "androidx.appcompat:appcompat:${Versions.support}"
    const val supportRecyclerview = "androidx.recyclerview:recyclerview:${Versions.support}"
    const val supportRecyclerviewSelection = "androidx.recyclerview:recyclerview-selection:${Versions.support}"
    const val supportCardview = "androidx.cardview:cardview:${Versions.supportCardView}"
    const val supportDesign = "com.google.android.material:material:${Versions.supportDesign}"
    const val supportLegacyV4 = "androidx.legacy:legacy-support-v4:${Versions.supportLegacy}"

    // Constraint Layout
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLaout}"

    // databinding compiler
    const val bindingCompiler = "androidx.databinding:databinding-compiler:${Versions.androidPlugin}"

    // room database
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomRxjava2 = "androidx.room:room-rxjava2:${Versions.room}"

    // lifecycle
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycleJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val lifecycleSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycleSaved}"

    // RxKotlin & RxJava
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"

    // retrofit
    const val retrofitRuntime = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitMock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
    const val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val okLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okLogging}"

    // Navigation
    const val navigationRuntime = "androidx.navigation:navigation-runtime:${Versions.navigation}"
    const val navigationRuntimeKtx = "androidx.navigation:navigation-runtime-ktx:${Versions.navigation}"
    const val navigationFragment = "androidx.navigation:navigation-fragment:${Versions.navigation}"
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui:${Versions.navigation}"

    // Glide for image loader
    const val glideRuntime = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    // Permission for AndroidX
    const val permission = "pub.devrel:easypermissions:${Versions.easyPermission}"

    // Timber for logging
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    // JUnit for testing
    const val junit = "junit:junit:${Versions.junit}"

    // KTX testing
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val kotlinAllopen = "org.jetbrains.kotlin:kotlin-allopen:${Versions.kotlin}"

    // Ruler & Runner testing
    const val atslRunner = "androidx.test:runner:$${Versions.atslRunner}"
    const val atslRules = "androidx.test:rules:${Versions.atslRules}"

    // espresso for testing
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    const val espressoIntents = "androidx.test.espresso:espresso-intents:${Versions.espresso}"

    // robolectric for testing
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"

    // Mockito for testing
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoAll = "org.mockito:mockito-all:${Versions.mockitoAll}"
    const val mockitoWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebserver}"

    // Hamcrest for testing
    const val hamcrest = "org.hamcrest:hamcrest-all:${Versions.hamcrest}"

    //
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // KOIN
    const val koinCore = "org.koin:koin-core:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koinJava = "org.koin:koin-java:${Versions.koin}"

    const val koinTest = "org.koin:koin-test:${Versions.koin}"

    // Dagger 2
    const val daggerCore = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"

    const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}