package com.example.cleanarchitecture.data.di

import com.example.cleanarchitecture.data.remote.api.ContributorApi
import com.example.cleanarchitecture.data.remote.api.ItemApi
import com.example.cleanarchitecture.data.remote.api.UserApi
import com.example.cleanarchitecture.data.remote.builder.RetrofitBuilder
import com.example.cleanarchitecture.data.remote.interceptor.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(retrofitBuilder: RetrofitBuilder, headerInterceptor: HeaderInterceptor): Retrofit = retrofitBuilder
        .addInterceptors(headerInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideUsersApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideItemsApi(retrofit: Retrofit): ItemApi = retrofit.create(ItemApi::class.java)

    @Provides
    @Singleton
    fun provideContributorApi(retrofit: Retrofit): ContributorApi = retrofit.create(ContributorApi::class.java)
}