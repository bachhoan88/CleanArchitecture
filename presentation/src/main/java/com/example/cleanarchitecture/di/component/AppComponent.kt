package com.example.cleanarchitecture.di.component

import com.example.cleanarchitecture.MainApplication
import com.example.cleanarchitecture.di.builder.AppModule
import com.example.cleanarchitecture.di.builder.MainActivityModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, MainActivityModule::class])
interface AppComponent : AndroidInjector<MainApplication> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<MainApplication>
}