package com.example.cleanarchitecture.domain.model

import io.reactivex.Observable

abstract class Mapper<in E, T> {
    abstract fun mapFrom(from: E): T

    fun observable(from: E): Observable<T> {
        return Observable.fromCallable { mapFrom(from) }
    }

    fun observable(from: List<E>): Observable<List<T>> {
        return Observable.fromCallable { from.map { mapFrom(it) } }
    }
}
