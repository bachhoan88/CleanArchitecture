package com.example.cleanarchitecture.data.remote.auth

import android.content.Context
import android.os.ConditionVariable
import com.example.cleanarchitecture.data.*
import com.example.cleanarchitecture.data.local.pref.AppPrefs
import com.example.cleanarchitecture.data.model.Token
import com.example.cleanarchitecture.data.remote.api.OAuthApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OauthRefreshAuthenticator @Inject constructor(@ApplicationContext private val context: Context) : Authenticator {

    private val prefHelper by lazy { AppPrefs(context, Gson()) }
    private val isRefreshing = AtomicBoolean(false)
    private val lock = ConditionVariable(true)

    override fun authenticate(route: Route, response: Response): Request? {
        Timber.d("Authentication error: ${response.code()} base on ${response.request().url()}")
        when (hasBearer(response)) {
            false -> {
                Timber.d("Not found bearer to refresh authentication")
                callLogout()
                return null
            }
            true -> {
                Timber.d("Authentication expired or bad request")
                val count = retryCount(response) + 1

                if (isRefreshing.compareAndSet(false, true)) {
                    lock.close()

                    val tokenResponse = refreshAuthentication(count)
                    val token = tokenResponse?.body()

                    when (tokenResponse != null && tokenResponse.isSuccessful && tokenResponse.code() == 200 && token != null) {
                        true -> {
                            prefHelper.setToken(token)

                            lock.open()
                            isRefreshing.set(false)

                            return reCallRequest(response.request(), count, token)
                        }

                        else -> {
                            lock.close()
                            callLogout()
                        }
                    }
                } else {

                    lock.block(HttpClient.CONNECTION_TIME_OUT_MLS)
                    prefHelper.getToken()?.let {
                        return reCallRequest(response.request(), count, it)
                    }
                }

                return null
            }
        }
    }

    @Synchronized
    private fun refreshAuthentication(retryCount: Int): retrofit2.Response<Token>? {
        if (retryCount > Authentication.MAX_RETRY) {
            Timber.d("Retry count maximum")
            return null
        }

        val refreshToken = prefHelper.getToken()?.token
        if (refreshToken.isNullOrBlank()) {
            return null
        }

        val clientBuilder = OkHttpClient.Builder().apply {
            connectTimeout(HttpClient.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(HttpClient.READ_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(HttpClient.WRITE_TIMEOUT, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
        }

        val gBuilder = GsonBuilder()
            .setLenient()
            .disableHtmlEscaping()
            .create()
        val factory = GsonConverterFactory.create(gBuilder)

        val retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url))
            .client(clientBuilder.build())
            .addConverterFactory(factory)
            .build()

        val authApi = retrofit.create(OAuthApi::class.java)
        return authApi.refreshToken(refreshToken).execute()
    }

    private fun callLogout() { }

    private fun hasBearer(response: Response): Boolean {
        return response.request()?.header(AUTH)?.startsWith(BEARER) ?: false
    }

    private fun retryCount(response: Response) = response.request()?.header(RETRY)?.toIntOrNull() ?: 0

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