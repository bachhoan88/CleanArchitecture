package com.example.cleanarchitecture

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.example.cleanarchitecture.crashlytics.CrashlyticsTree
import com.example.cleanarchitecture.data.BuildConfig
import timber.log.Timber
import com.crashlytics.android.core.CrashlyticsCore
import dagger.hilt.android.HiltAndroidApp
import io.fabric.sdk.android.Fabric

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val crashlytics = CrashlyticsCore.Builder()
            .disabled(BuildConfig.DEBUG)
            .build()
        Fabric.with(this, Crashlytics.Builder()
            .core(crashlytics).build())

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Timber.plant(CrashlyticsTree())
    }
}