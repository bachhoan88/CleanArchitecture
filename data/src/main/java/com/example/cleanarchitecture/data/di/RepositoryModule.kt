package com.example.cleanarchitecture.data.di

import androidx.room.Room
import android.content.Context
import com.example.cleanarchitecture.data.Constants
import com.example.cleanarchitecture.data.ContributorRepositoryImpl
import com.example.cleanarchitecture.data.ItemRepositoryImpl
import com.example.cleanarchitecture.data.UserRepositoryImpl
import com.example.cleanarchitecture.data.local.db.AppDatabase
import com.example.cleanarchitecture.data.local.pref.AppPrefs
import com.example.cleanarchitecture.data.local.pref.PrefHelper
import com.example.cleanarchitecture.domain.repository.ContributorRepository
import com.example.cleanarchitecture.domain.repository.ItemRepository
import com.example.cleanarchitecture.domain.repository.UserRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @DatabaseInfo
    fun providerDatabaseName(): String {
        return Constants.DATABASE_NAME
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@DatabaseInfo dbName: String, @ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName).fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Singleton
    fun providePrefHelper(appPrefs: AppPrefs): PrefHelper {
        return appPrefs
    }

    @Provides
    @Singleton
    fun providerAppPrefs(@ApplicationContext context: Context): AppPrefs {
        return AppPrefs(context, Gson())
    }

    @Provides
    @Singleton
    fun providerUserRepository(repository: UserRepositoryImpl): UserRepository {
        return repository
    }

    @Provides
    @Singleton
    fun providerItemRepository(repository: ItemRepositoryImpl): ItemRepository {
        return repository
    }

    @Provides
    @Singleton
    fun providerContributorRepository(repository: ContributorRepositoryImpl): ContributorRepository {
        return repository
    }
}
