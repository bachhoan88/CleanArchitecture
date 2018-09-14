package com.example.cleanarchitecture.di.component

import com.example.cleanarchitecture.MainApplication
import com.example.cleanarchitecture.di.builder.AppModule
import com.example.cleanarchitecture.di.builder.MainActivityModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class))
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MainApplication>()
}