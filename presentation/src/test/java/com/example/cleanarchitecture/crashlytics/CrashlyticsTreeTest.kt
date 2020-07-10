package com.example.cleanarchitecture.crashlytics

import org.junit.Test
import timber.log.Timber

class CrashlyticsTreeTest {
    private val crashlytics = CrashlyticsTree()

    @Test
    fun testDebug() {
        val throwable = Throwable("abc")
        Timber.d(throwable)
    }
}