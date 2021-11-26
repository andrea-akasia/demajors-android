package com.demajors.demajorsapp.util

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class CrashlyticsTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        } else if (priority == Log.INFO) {
            FirebaseCrashlytics.getInstance().log(message)
        } else {
            t?.let { FirebaseCrashlytics.getInstance().recordException(t) }
        }
    }
}
