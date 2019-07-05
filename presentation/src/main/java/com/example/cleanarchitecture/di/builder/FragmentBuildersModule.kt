package com.example.cleanarchitecture.di.builder

import com.example.cleanarchitecture.ui.main.MainFragment
import com.example.cleanarchitecture.ui.splash.SplashFragment
import com.example.cleanarchitecture.ui.tutorial.TutorialFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeTutorialFragment(): TutorialFragment
}
