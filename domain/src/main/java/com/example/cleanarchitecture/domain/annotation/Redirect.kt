package com.example.cleanarchitecture.domain.annotation

import androidx.annotation.IntDef
import com.example.cleanarchitecture.domain.annotation.Redirect.Companion.OPEN_HOME_SCREEN

@IntDef(OPEN_HOME_SCREEN)
annotation class Redirect {
    companion object {
        const val OPEN_HOME_SCREEN = 1
    }
}