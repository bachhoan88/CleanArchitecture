package com.example.cleanarchitecture.data.remote.mapper

import android.content.Context
import com.example.cleanarchitecture.data.R
import com.example.cleanarchitecture.data.remote.exception.RetrofitException
import com.example.cleanarchitecture.domain.exception.AlertException
import com.example.cleanarchitecture.domain.exception.SnackBarException
import javax.inject.Inject

/**
 * This is class example mapper from Retrofit response exception to `CleanException.class`
 * Please change your requirements
 */
class RetrofitExceptionMapper @Inject constructor(private val context: Context) {

    fun mapToCleanException(retrofitException: RetrofitException): Throwable {
        return when (val kind = retrofitException.getKind()) {
            RetrofitException.Kind.NETWORK ->
                SnackBarException(
                    code = -1,
                    message = context.getString(R.string.internet_connection_error)
                )

            RetrofitException.Kind.HTTP ->
                AlertException(
                    code = retrofitException.getResponse()?.code() ?: -1,
                    title = String.format(context.getString(R.string.error_code_title), retrofitException.getResponse()?.code()),
                    message = String.format(
                        context.getString(R.string.do_not_connection_url),
                        retrofitException.getRetrofit()?.baseUrl() ?: ""
                    )
                )

            else -> retrofitException
        }
    }
}