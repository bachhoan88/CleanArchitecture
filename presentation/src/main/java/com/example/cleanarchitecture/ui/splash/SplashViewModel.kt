package com.example.cleanarchitecture.ui.splash

import com.example.cleanarchitecture.base.BaseViewModel
import com.example.cleanarchitecture.rx.SchedulerProvider
import javax.inject.Inject

class SplashViewModel @Inject constructor(
        private val mSchedulerProvider: SchedulerProvider
) : BaseViewModel<SplashNavigator>() {

}
