package com.example.cleanarchitecture.data.remote.factory

import java.lang.reflect.Type

import retrofit2.CallAdapter
import retrofit2.Retrofit

class RxErrorHandlingCallAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        return null
    }

    companion object {

        fun create(): CallAdapter.Factory {
            return RxErrorHandlingCallAdapterFactory()
        }
    }
}