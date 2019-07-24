package com.example.cleanarchitecture.data.remote.auth

import android.content.Context
import com.example.cleanarchitecture.data.Authentication
import com.example.cleanarchitecture.data.model.Token
import com.example.cleanarchitecture.data.remote.api.OAuthApi
import com.example.cleanarchitecture.data.remote.builder.RetrofitBuilder
import okhttp3.*
import retrofit2.Call
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class OauthRefreshAuthenticator @Inject constructor(private val context: Context) : Authenticator {

    override fun authenticate(route: Route, response: Response): Request? {
        Timber.d("Authentication error: ${response.code()} base on ${response.request()?.url()}")
        return when (hasBearer(response)) {
            false -> {
                Timber.d("Not found bearer to refresh authentication")
                null
            }
            true -> {
                Timber.d("Authentication expired or bad request")
                refreshAuthentication(response.request(), retryCount(response) + 1)
            }
        }
    }

    @Synchronized
    private fun refreshAuthentication(request: Request?, retryCount: Int): Request? {
        if (retryCount > Authentication.MAX_RETRY) {
            Timber.d("Retry count maximum")
            return null
        }

        val authApi = RetrofitBuilder(context).build().create(OAuthApi::class.java)
        authApi.getAccessToken("refresh_token", "mobile").enqueue(object : retrofit2.Callback<Token> {
            override fun onFailure(call: Call<Token>, t: Throwable) {
                Timber.e(t)
            }

            override fun onResponse(call: Call<Token>, response: retrofit2.Response<Token>) {
                Timber.d("Refresh access token successful!")
                when (response.isSuccessful) {
                    true -> response.body()?.let { reCallRequest(request, retryCount, it) }
                }
            }
        })

        return null
    }

    private fun hasBearer(response: Response): Boolean {
        return response.request()?.header(AUTH)?.startsWith(BEARER) ?: false
    }

    private fun retryCount(response: Response): Int {
        val retry = response.request()?.header(RETRY)
        return try {
            retry?.toIntOrNull() ?: 0
        } catch (exception: Exception) {
            0
        }
    }

    private fun reCallRequest(request: Request?, retryCount: Int, token: Token): Request? {
        return request?.newBuilder()
            ?.header(RETRY, retryCount.toString())
            ?.header(AUTH, "$BEARER ${token.token}")
            ?.build()
    }

    companion object {
        private const val AUTH = "Authorization"
        private const val RETRY = "Authorization_retry_count"
        private const val BEARER = "Bearer"
    }
}