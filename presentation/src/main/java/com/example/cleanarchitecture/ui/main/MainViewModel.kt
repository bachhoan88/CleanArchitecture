package com.example.cleanarchitecture.ui.main

import android.arch.lifecycle.MutableLiveData
import com.example.cleanarchitecture.base.BaseViewModel
import com.example.cleanarchitecture.domain.usecase.UserUseCase
import com.example.cleanarchitecture.model.UserItem
import com.example.cleanarchitecture.model.UserItemMapper
import com.example.cleanarchitecture.rx.SchedulerProvider
import java.util.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val mUseCase: UserUseCase,
        private val mSchedulerProvider: SchedulerProvider,
        private val mMapper: UserItemMapper
) : BaseViewModel<MainNavigator>() {

    val user = MutableLiveData<UserItem>()
    val userId = MutableLiveData<String>()


    fun searchUser(originalInput: String) {
        val input = originalInput.toLowerCase(Locale.getDefault()).trim()
        if (input == userId.value) {
            return
        } else {
            userId.value = input
        }

        userId.value?.let {
            if (it.isNotBlank()) {
                compositeDisposable!!.add(mUseCase.getUser(it, true)
                        .subscribeOn(mSchedulerProvider.io())
                        .observeOn(mSchedulerProvider.ui())
                        .map { mMapper.mapToPresentation(it) }
                        .subscribe({ user.postValue(it) }, {})
                )
            }
        }
    }

    fun actionSearch() {
        searchUser(userId.value!!)
    }

}
