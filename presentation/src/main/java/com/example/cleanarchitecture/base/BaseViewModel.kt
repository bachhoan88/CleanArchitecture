package com.example.cleanarchitecture.base

import android.arch.lifecycle.ViewModel
import com.example.cleanarchitecture.domain.usecase.UseCase
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<N> constructor(
        vararg val useCases: UseCase<*, *>?
) : ViewModel() {

    var navigator: N? = null
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        useCases.let { if (it.isNotEmpty()) it.forEach { it!!.onCleared() } }
        super.onCleared()
    }
}
