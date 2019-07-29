package com.example.cleanarchitecture.extension

import com.example.cleanarchitecture.base.BaseViewModel
import io.reactivex.disposables.Disposable

fun Disposable.add(viewModel: BaseViewModel) {
    viewModel.addDisposable(this)
}