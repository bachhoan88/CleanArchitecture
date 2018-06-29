package com.example.cleanarchitecture.ui.main

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.example.cleanarchitecture.RxSchedulersOverrideRule
import com.example.cleanarchitecture.createUser
import com.example.cleanarchitecture.domain.usecase.user.FindUserUseCase
import com.example.cleanarchitecture.mock
import com.example.cleanarchitecture.model.UserItem
import com.example.cleanarchitecture.model.UserItemMapper
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Unit Test for [MainViewModel]
 */
class MainViewModelTest {
    private lateinit var mainViewModel: MainViewModel

    @Mock
    private lateinit var userUseCase: FindUserUseCase

    private val schedulerProvider = com.example.cleanarchitecture.rx.AppSchedulerProvider()

    private val userItemMapper = UserItemMapper()

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

        mainViewModel = MainViewModel(userUseCase, schedulerProvider, userItemMapper)
    }

    @Test
    fun searchNull() {
        mainViewModel.userId.value = null

        mainViewModel.searchUser()

        assertThat(mainViewModel.user.value, nullValue())
    }

    @Test
    fun testSearchUserId() {
        val id = "1"
        mainViewModel.userId.value = id

        val user = createUser()
        given(userUseCase.createObservable(FindUserUseCase.Params(id, true))).willReturn(Single.just(user))

        val observer = mock<Observer<UserItem>>()
        mainViewModel.user.observeForever(observer)

        mainViewModel.searchUser()

        assertEquals(mainViewModel.user.value, userItemMapper.mapToPresentation(user))
    }
}