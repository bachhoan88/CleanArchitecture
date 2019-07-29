package com.example.cleanarchitecture.data.local.pref

import com.example.cleanarchitecture.data.model.Token

interface PrefHelper {
    fun isFirstRun(): Boolean

    fun setFirstRun(boolean: Boolean)

    fun getToken(): Token?

    fun setToken(token: Token)
}