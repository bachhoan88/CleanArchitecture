package com.example.cleanarchitecture.data.remote.builder

import android.content.Context
import com.example.cleanarchitecture.data.BuildConfig
import com.example.cleanarchitecture.data.HttpClient
import com.example.cleanarchitecture.data.R
import com.example.cleanarchitecture.data.remote.auth.OauthRefreshAuthenticator
import com.example.cleanarchitecture.data.remote.factory.RxErrorHandlingCallAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitBuilder @Inject constructor(@ApplicationContext private val context: Context) {
    private var connectionTimeout = HttpClient.CONNECT_TIMEOUT
    private var writeTimeout = HttpClient.WRITE_TIMEOUT
    private var readTimeout = HttpClient.READ_TIMEOUT
    private var okHttpClientBuilder: OkHttpClient.Builder? = null
    private var interceptors = mutableListOf<Interceptor>()
    private var logEnable: Boolean = BuildConfig.DEBUG
    private var isSupportAuthorization = false
    private var authenticator: Authenticator? = null
    private var baseUrl: String = context.getString(R.string.base_url)

    @Inject
    lateinit var oauthRefreshAuthenticator: OauthRefreshAuthenticator

    /**
     * Customize time out
     * @param connectionTimeout timeout for connection OK Http client
     * @param writeTimeout timeout for write data
     * @param readTimeout timeout for read data
     */
    fun setTimeout(
        connectionTimeout: Long = HttpClient.CONNECT_TIMEOUT,
        writeTimeout: Long = HttpClient.WRITE_TIMEOUT,
        readTimeout: Long = HttpClient.READ_TIMEOUT
    ): RetrofitBuilder {
        this.connectionTimeout = connectionTimeout
        this.writeTimeout = writeTimeout
        this.readTimeout = readTimeout
        return this
    }

    /**
     * User customize ok http client
     * @param okHttpClientBuilder
     */
    fun setOkHttpClientBuilder(okHttpClientBuilder: OkHttpClient.Builder): RetrofitBuilder {
        this.okHttpClientBuilder = okHttpClientBuilder
        return this
    }

    /**
     * add custom interceptor for ok http client
     * @param interceptor is a interceptor for ok http client
     */
    fun addInterceptors(vararg interceptor: Interceptor): RetrofitBuilder {
        interceptors.addAll(interceptor)
        return this
    }

    /**
     * Customize show or hide logging
     * @param enable is status for logs
     */
    fun loggingEnable(enable: Boolean): RetrofitBuilder {
        this.logEnable = enable
        return this
    }

    /**
     * Support default Authorization
     * @param enable is status support
     */
    fun supportAuthorization(enable: Boolean): RetrofitBuilder {
        this.isSupportAuthorization = enable
        return this
    }

    /**
     * Customize authorization
     * @param authenticator
     */
    fun setCustomAuthorization(authenticator: Authenticator): RetrofitBuilder {
        this.authenticator = authenticator
        return this
    }

    /**
     * Customize base url
     * @param baseUrl is base url for ok http client
     */
    fun setBaseURL(baseUrl: String): RetrofitBuilder {
        this.baseUrl = baseUrl
        return this
    }

    /**
     * Make a Retrofit
     */
    fun build(): Retrofit {
        val clientBuilder = okHttpClientBuilder?.let { it } ?: OkHttpClient.Builder().apply {
            connectTimeout(connectionTimeout, TimeUnit.SECONDS)
            writeTimeout(writeTimeout, TimeUnit.SECONDS)
            readTimeout(readTimeout, TimeUnit.SECONDS)

            if (logEnable) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }

            val auth: Authenticator? = when {
                authenticator != null -> authenticator
                isSupportAuthorization -> oauthRefreshAuthenticator
                else -> null
            }

            auth?.let { authenticator(it) }

            interceptors.forEach { addInterceptor(it) }
        }

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(clientBuilder.build())
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}