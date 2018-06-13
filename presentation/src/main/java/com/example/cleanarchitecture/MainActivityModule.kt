package com.example.cleanarchitecture

import android.arch.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.ui.main.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    fun mainViewModel(mainViewModel: MainViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(mainViewModel)
    }
}