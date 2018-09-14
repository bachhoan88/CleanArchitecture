package com.example.cleanarchitecture.ui.login

import com.example.cleanarchitecture.base.BaseViewModel
import com.example.cleanarchitecture.rx.SchedulerProvider
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

}
