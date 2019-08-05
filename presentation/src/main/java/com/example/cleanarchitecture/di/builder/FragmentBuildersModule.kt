package com.example.cleanarchitecture.di.builder

import com.example.cleanarchitecture.MainActivity
import com.example.cleanarchitecture.ui.contributor.ContributorFragment
import com.example.cleanarchitecture.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeRepoDetailFragment(): ContributorFragment
}
