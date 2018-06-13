package com.example.cleanarchitecture.ui.main

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentProvider {
    @ContributesAndroidInjector(modules = arrayOf(MainFragmentModule::class))
    abstract fun providerMainFragmentFactory(): MainFragment
}
