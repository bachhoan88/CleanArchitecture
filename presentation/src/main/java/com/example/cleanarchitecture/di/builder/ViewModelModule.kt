package com.example.cleanarchitecture.di.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.ViewModelProviderFactory
import com.example.cleanarchitecture.di.annotation.ViewModelKey
import com.example.cleanarchitecture.ui.contributor.ContributorViewModel
import com.example.cleanarchitecture.ui.main.MainViewModel
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
    @ViewModelKey(ContributorViewModel::class)
    abstract fun bindRepoDetailViewModel(contributorViewModel: ContributorViewModel): ViewModel
}