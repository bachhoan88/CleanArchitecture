package com.example.cleanarchitecture.ui.main

import com.example.cleanarchitecture.domain.usecase.user.FindUserUseCase
import com.example.cleanarchitecture.model.UserItemMapper
import com.example.cleanarchitecture.rx.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class MainFragmentModule {
    @Provides
    fun bindMainViewModel(userUseCase: FindUserUseCase,
                          schedulerProvider: SchedulerProvider,
                          userItemMapper: UserItemMapper): MainViewModel {
        return MainViewModel(userUseCase, schedulerProvider, userItemMapper)
    }
}