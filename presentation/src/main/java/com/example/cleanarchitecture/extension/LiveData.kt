package com.example.cleanarchitecture.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

fun <X, Y> LiveData<X>.map(func: (X?) -> Y?): MutableLiveData<Y?> {
    return MediatorLiveData<Y>().apply {
        addSource(this@map) { x -> value = func(x) }
    }
}

fun <X, Y> LiveData<X>.mapSkipNulls(func: (X) -> Y): MutableLiveData<Y> {
    return MediatorLiveData<Y>().apply {
        addSource(this@mapSkipNulls) { x ->
            x ?: return@addSource
            value = func(x)
        }
    }
}

fun <A, B> LiveData<A>.combineLatest(b: LiveData<B>): LiveData<Pair<A, B>> {
    return MediatorLiveData<Pair<A, B>>().apply {
        var lastA: A? = null
        var lastB: B? = null

        addSource(this@combineLatest) {
            if (it == null && value != null) value = null
            lastA = it
            if (lastA != null && lastB != null) value = lastA!! to lastB!!
        }

        addSource(b) {
            if (it == null && value != null) value = null
            lastB = it
            if (lastA != null && lastB != null) value = lastA!! to lastB!!
        }
    }
}

fun <A, B, C> combineLatest(a: LiveData<A>, b: LiveData<B>, c: LiveData<C>): LiveData<Triple<A?, B?, C?>> {

    fun Triple<A?, B?, C?>?.copyWithFirst(first: A?): Triple<A?, B?, C?> {
        if (this@copyWithFirst == null) return Triple<A?, B?, C?>(first, null, null)
        return this@copyWithFirst.copy(first = first)
    }

    fun Triple<A?, B?, C?>?.copyWithSecond(second: B?): Triple<A?, B?, C?> {
        if (this@copyWithSecond == null) return Triple<A?, B?, C?>(null, second, null)
        return this@copyWithSecond.copy(second = second)
    }

    fun Triple<A?, B?, C?>?.copyWithThird(third: C?): Triple<A?, B?, C?> {
        if (this@copyWithThird == null) return Triple<A?, B?, C?>(null, null, third)
        return this@copyWithThird.copy(third = third)
    }

    return MediatorLiveData<Triple<A?, B?, C?>>().apply {
        addSource(a) { value = value.copyWithFirst(it) }
        addSource(b) { value = value.copyWithSecond(it) }
        addSource(c) { value = value.copyWithThird(it) }
    }
}