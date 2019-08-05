package com.example.cleanarchitecture

import com.example.cleanarchitecture.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class MainApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<MainApplication> = DaggerAppComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}