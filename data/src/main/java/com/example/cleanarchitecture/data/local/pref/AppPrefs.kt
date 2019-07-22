package com.example.cleanarchitecture.data.local.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.cleanarchitecture.data.model.Token
import com.google.gson.Gson
import javax.inject.Inject

class AppPrefs @Inject constructor(
    private val mContext: Context,
    private val gson: Gson
) : PrefHelper {

    private val sharedPreferences: SharedPreferences =
        mContext.getSharedPreferences(mContext.packageName, Context.MODE_PRIVATE)

    override fun getToken(): Token? {
        return sharedPreferences.getString(TOKEN, null)
            ?.let { gson.fromJson(it, Token::class.java) }
    }

    override fun setToken(token: Token) {
        sharedPreferences.edit { putString(TOKEN, gson.toJson(token)) }
    }

    override fun setFirstRun(boolean: Boolean) {
        sharedPreferences.edit { putBoolean(FIRST_RUN, boolean) }
    }

    override fun isFirstRun(): Boolean {
        return sharedPreferences.getBoolean(FIRST_RUN, true)
    }

    companion object {
        private const val FIRST_RUN = "first_run"
        private const val TOKEN = "token"
    }
}