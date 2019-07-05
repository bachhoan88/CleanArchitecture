package com.example.cleanarchitecture.data.local.pref

import android.content.Context
import androidx.core.content.edit
import javax.inject.Inject

class AppPrefs @Inject constructor(
    mContext: Context
) : PrefHelper {
    companion object {
        private const val FIRST_RUN = "first_run"
    }

    var sharedPreferences = mContext.getSharedPreferences(mContext.packageName, Context.MODE_PRIVATE)

    override fun isFirstRun(): Boolean {
        val first = sharedPreferences.getBoolean(FIRST_RUN, true)
        if (first) {
            sharedPreferences.edit { putBoolean(FIRST_RUN, false) }
        }

        return first
    }
}