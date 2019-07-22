package com.example.cleanarchitecture.di.builder

import android.content.Context
import com.example.cleanarchitecture.MainApplication
import com.example.cleanarchitecture.data.di.NetworkModule
import com.example.cleanarchitecture.data.di.RepositoryModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, FragmentBuildersModule::class, NetworkModule::class, RepositoryModule::class])
class AppModule {

    @Singleton
    @Provides
    fun providerContext(application: MainApplication): Context {
        return application.applicationContext
    }
}