package com.example.cleanarchitecture.ui.tutorial

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cleanarchitecture.RxSchedulersOverrideRule
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

/**
 * Unit Test for [TutorialViewModel]
 */
class TutorialViewModelTest {
    private lateinit var tutorialViewModel: TutorialViewModel

    @Rule
    @JvmField
    val rxSchedulersOverrideRule: RxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        tutorialViewModel = TutorialViewModel()
    }
}