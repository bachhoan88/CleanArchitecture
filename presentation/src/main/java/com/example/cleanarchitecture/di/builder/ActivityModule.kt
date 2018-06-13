package com.example.cleanarchitecture.di.builder
import com.example.cleanarchitecture.MainActivity
import com.example.cleanarchitecture.MainActivityModule
import com.example.cleanarchitecture.ui.main.MainFragmentProvider

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class, MainFragmentProvider::class))
    abstract fun contributeMainActivity(): MainActivity
}
