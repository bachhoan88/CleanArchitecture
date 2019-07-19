package com.example.cleanarchitecture.di.builder

import android.app.Application
import android.content.Context
import com.example.cleanarchitecture.data.di.NetworkModule
import com.example.cleanarchitecture.data.di.RepositoryModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, FragmentBuildersModule::class, NetworkModule::class, RepositoryModule::class])
class AppModule {

    @Singleton
    @Provides
    fun providerContext(application: Application): Context {
        return application
    }
}