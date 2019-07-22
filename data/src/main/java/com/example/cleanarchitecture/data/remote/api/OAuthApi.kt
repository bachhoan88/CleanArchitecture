package com.example.cleanarchitecture.data.remote.api

import com.example.cleanarchitecture.data.model.Token
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface OAuthApi {

    @POST("/oauth/token")
    @FormUrlEncoded
    fun getAccessToken(@Field("grant_type") grantType: String,
                       @Field("client_id")clientId: String): Call<Token>
}