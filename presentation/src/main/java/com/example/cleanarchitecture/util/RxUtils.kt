@file:Suppress("UNCHECKED_CAST")

package com.example.cleanarchitecture.util

import androidx.lifecycle.MutableLiveData
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

internal object RxUtils {
    fun <T> applyObservableScheduler(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> applyObservableScheduler(loading: MutableLiveData<Boolean>): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading.postValue(true) }
                .doFinally { loading.postValue(false) }
        }
    }

    fun <T> applyFlowableScheduler(): FlowableTransformer<T, T> {
        return FlowableTransformer<Flowable<*>, Flowable<*>> { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        } as FlowableTransformer<T, T>
    }

    fun <T> applyFlowableScheduler(loading: MutableLiveData<Boolean>): FlowableTransformer<T, T> {
        return FlowableTransformer<Flowable<*>, Flowable<*>> { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading.postValue(true) }
                .doFinally { loading.postValue(false) }
        } as FlowableTransformer<T, T>
    }

    fun <T> applySingleScheduler(): SingleTransformer<T, T> {
        return SingleTransformer<Single<*>, Single<*>> { upstream ->
            upstream.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
        } as SingleTransformer<T, T>
    }

    fun <T> applySingleScheduler(loading: MutableLiveData<Boolean>): SingleTransformer<T, T> {
        return SingleTransformer<Single<*>, Single<*>> { upstream ->
            upstream.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { loading.postValue(true) }
                .doFinally { loading.postValue(false) }
        } as SingleTransformer<T, T>
    }

    fun applyCompletableScheduler(): CompletableTransformer {
        return CompletableTransformer { upstream ->
            upstream.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
        }
    }

    fun applyCompletableScheduler(loading: MutableLiveData<Boolean>): CompletableTransformer {
        return CompletableTransformer { upstream ->
            upstream.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { loading.postValue(true) }
                .doFinally { loading.postValue(false) }
        }
    }

    fun <T> applyMaybeScheduler(): MaybeTransformer<T, T> {
        return MaybeTransformer<Maybe<*>, Maybe<*>> { upstream ->
            upstream.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
        } as MaybeTransformer<T, T>
    }

    fun <T> applyMaybeScheduler(loading: MutableLiveData<Boolean>): MaybeTransformer<T, T> {
        return MaybeTransformer<Maybe<*>, Maybe<*>> { upstream ->
            upstream.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { loading.postValue(true) }
                .doFinally { loading.postValue(false) }
        } as MaybeTransformer<T, T>
    }
}