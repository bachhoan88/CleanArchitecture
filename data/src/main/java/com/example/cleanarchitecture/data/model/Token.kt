package com.example.cleanarchitecture.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Token(
    @Expose @SerializedName("token") val token: String,
    @Expose @SerializedName("refresh_token") val refreshToken: String,
    @Expose @SerializedName("expires_in") val expireIn: Long
)