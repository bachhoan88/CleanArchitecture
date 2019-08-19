package com.example.cleanarchitecture.crashlytics

import android.util.Log
import com.crashlytics.android.Crashlytics
import timber.log.Timber

class CrashlyticsTree : Timber.Tree() {

    companion object {
        private const val KEY_PRIORITY = "priority"
        private const val KEY_TAG = "tag"
        private const val KEY_MESSAGE = "message"
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
            Log.VERBOSE, Log.DEBUG, Log.INFO -> return

            else -> {
                Crashlytics.setInt(KEY_PRIORITY, priority)
                Crashlytics.setString(KEY_TAG, tag)
                Crashlytics.setString(KEY_MESSAGE, message)

                when (t == null) {
                    true -> Crashlytics.logException(Exception(message))
                    false -> Crashlytics.logException(t)
                }
            }
        }
    }
}
