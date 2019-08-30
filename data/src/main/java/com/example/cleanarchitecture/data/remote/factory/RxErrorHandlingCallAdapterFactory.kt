package com.example.cleanarchitecture.data.remote.factory

import android.content.Context
import com.example.cleanarchitecture.data.remote.exception.RetrofitException
import com.example.cleanarchitecture.data.remote.mapper.RetrofitExceptionMapper
import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type

class RxErrorHandlingCallAdapterFactory(private val context: Context) : CallAdapter.Factory() {

    private val _original by lazy {
        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    companion object {
        fun create(context: Context): CallAdapter.Factory =
            RxErrorHandlingCallAdapterFactory(context)
    }

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *> {
        val wrapped = _original.get(returnType, annotations, retrofit) as CallAdapter<out Any, *>
        return RxCallAdapterWrapper(context, retrofit, wrapped)
    }

    private class RxCallAdapterWrapper<R>(
        val _context: Context,
        val _retrofit: Retrofit,
        val _wrappedCallAdapter: CallAdapter<R, *>
    ) : CallAdapter<R, Any> {

        private val retrofitExceptionMapper by lazy { RetrofitExceptionMapper(_context) }

        override fun responseType(): Type = _wrappedCallAdapter.responseType()

        @Suppress("UNCHECKED_CAST")
        override fun adapt(call: Call<R>): Any {
            return when (val adapted = _wrappedCallAdapter.adapt(call)) {
                is Single<*> -> adapted.onErrorResumeNext { throwable: Throwable ->
                    Single.error(
                        retrofitExceptionMapper.mapToCleanException(
                            asRetrofitException(
                                throwable
                            )
                        )
                    )
                }

                is Observable<*> -> adapted.onErrorResumeNext { throwable: Throwable ->
                    Observable.error(
                        retrofitExceptionMapper.mapToCleanException(
                            asRetrofitException(
                                throwable
                            )
                        )
                    )
                }

                is Flowable<*> -> adapted.onErrorResumeNext { throwable: Throwable ->
                    Flowable.error(
                        retrofitExceptionMapper.mapToCleanException(
                            asRetrofitException(
                                throwable
                            )
                        )
                    )
                }

                is Completable -> adapted.onErrorResumeNext { throwable: Throwable ->
                    Completable.error(
                        retrofitExceptionMapper.mapToCleanException(
                            asRetrofitException(throwable)
                        )
                    )
                }

                is Maybe<*> -> adapted.onErrorResumeNext { throwable: Throwable ->
                    Maybe.error(
                        retrofitExceptionMapper.mapToCleanException(
                            asRetrofitException(
                                throwable
                            )
                        )
                    )
                }

                else -> adapted
            }
        }

        private fun asRetrofitException(throwable: Throwable): RetrofitException {
            // We had non-200 http error
            if (throwable is HttpException) {
                val response = throwable.response()

                return when (throwable.code()) {
                    422 -> // on out api 422's get metadata in the response. Adjust logic here based on your needs
                        RetrofitException.httpErrorWithObject(
                            response.raw().request().url().toString(),
                            response,
                            _retrofit
                        )
                    else -> RetrofitException.httpError(
                        response.raw().request().url().toString(),
                        response,
                        _retrofit
                    )
                }
            }

            // A network error happened
            if (throwable is IOException) {
                return RetrofitException.networkError(throwable)
            }

            // We don't know what happened. We need to simply convert to an unknown error
            return RetrofitException.unexpectedError(throwable)
        }
    }
}