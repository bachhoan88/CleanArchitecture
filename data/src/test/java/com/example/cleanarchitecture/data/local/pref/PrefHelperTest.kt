package com.example.cleanarchitecture.data.local.pref

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21))
class PrefHelperTest {
    private lateinit var prefHelper: PrefHelper

    @Before
    fun setup() {
        prefHelper = AppPrefs(RuntimeEnvironment.application)
    }

    @Test
    fun checkFirstRun() {
        val firstRun = prefHelper.isFirstRun()
        assertEquals(firstRun, true)

        val otherRun = prefHelper.isFirstRun()
        assertEquals(otherRun, false)
    }
}