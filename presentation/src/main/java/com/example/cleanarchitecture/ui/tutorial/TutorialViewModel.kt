package com.example.cleanarchitecture.ui.tutorial

import com.example.cleanarchitecture.base.BaseViewModel
import com.example.cleanarchitecture.rx.SchedulerProvider
import javax.inject.Inject

/**
 * ViewModel for [TutorialFragment]
 */

class TutorialViewModel @Inject constructor(
        private val mSchedulerProvider: SchedulerProvider
) : BaseViewModel() {

}
