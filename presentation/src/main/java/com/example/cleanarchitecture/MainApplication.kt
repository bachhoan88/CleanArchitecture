package com.example.cleanarchitecture

import com.example.cleanarchitecture.di.component.DaggerAppComponent
import dagger.android.DaggerApplication

class MainApplication : DaggerApplication() {
    override fun applicationInjector() = DaggerAppComponent.builder().create(this)
}