package com.example.cleanarchitecture.ui.login

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cleanarchitecture.RxSchedulersOverrideRule
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import com.example.cleanarchitecture.rx.AppSchedulerProvider
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

/**
 * Unit Test for [LoginViewModel]
 */
class LoginViewModelTest {
    private lateinit var loginViewModel: LoginViewModel

    private val schedulerProvider = AppSchedulerProvider()

    @Rule
    @JvmField
    val rxSchedulersOverrideRule: RxSchedulersOverrideRule = RxSchedulersOverrideRule()


    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }

        loginViewModel = LoginViewModel(schedulerProvider)
    }
}