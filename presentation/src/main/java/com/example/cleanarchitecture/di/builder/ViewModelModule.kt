package com.example.cleanarchitecture.di.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.ViewModelProviderFactory
import com.example.cleanarchitecture.di.annotation.ViewModelKey
import com.example.cleanarchitecture.ui.main.MainViewModel
import com.example.cleanarchitecture.ui.splash.SplashViewModel
import com.example.cleanarchitecture.ui.tutorial.TutorialViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(providerFactory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TutorialViewModel::class)
    abstract fun bindTutorialViewModel(tutorialViewModel: TutorialViewModel): ViewModel
}
