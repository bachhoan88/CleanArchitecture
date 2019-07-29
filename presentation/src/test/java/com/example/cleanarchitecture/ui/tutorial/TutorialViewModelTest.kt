package com.example.cleanarchitecture.ui.tutorial

import com.example.cleanarchitecture.ui.BaseViewModelTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Unit Test for [TutorialViewModel]
 */
@RunWith(JUnit4::class)
class TutorialViewModelTest : BaseViewModelTest() {

    private lateinit var tutorialViewModel: TutorialViewModel

    @Test
    fun example() {
        assertEquals(1 + 1, 2)
    }
}