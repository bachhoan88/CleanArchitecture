package com.example.cleanarchitecture.di.builder

import android.app.Application
import android.content.Context
import com.example.cleanarchitecture.data.di.NetworkModule
import com.example.cleanarchitecture.data.di.RepositoryModule
import com.example.cleanarchitecture.rx.AppSchedulerProvider
import com.example.cleanarchitecture.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun providerContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun providerSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}