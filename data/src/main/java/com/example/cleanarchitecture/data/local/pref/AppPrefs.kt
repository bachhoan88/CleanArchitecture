package com.example.cleanarchitecture.data.local.pref

import android.content.Context
import javax.inject.Inject

class AppPrefs @Inject constructor(
        mContext: Context
) : PrefHelper {
    var sharedPreferences = mContext.getSharedPreferences(mContext.packageName, Context.MODE_PRIVATE)

    companion object {
        private const val FIRST_RUN = "first_run";
    }

    override fun isFirstRun(): Boolean {
        val first = sharedPreferences.getBoolean(FIRST_RUN, true)
        if (first) {
            sharedPreferences.edit().putBoolean(FIRST_RUN, false).apply()
        }

        return first
    }

}