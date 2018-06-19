package com.example.cleanarchitecture.data.remote.api

import com.example.cleanarchitecture.data.model.UserEntity
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {
    @GET("users/")
    fun getUsers(): Single<List<UserEntity>>

    @GET("users/{id}")
    fun getUser(@Path("id") userId: String): Single<UserEntity>

    @POST("users/signin")
    fun signin(@Path("username") userName: String, @Path("password") password: String): Completable
}