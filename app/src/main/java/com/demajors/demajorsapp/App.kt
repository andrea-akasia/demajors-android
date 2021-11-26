package com.demajors.demajorsapp

import android.app.Application
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject
import dagger.android.AndroidInjector
import com.demajors.demajorsapp.di.component.AppComponent
import com.demajors.demajorsapp.di.modules.NetworkModule
import com.demajors.demajorsapp.di.component.DaggerAppComponent
import com.demajors.demajorsapp.util.CrashlyticsTree
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase
import dagger.android.HasAndroidInjector
import timber.log.Timber

class App : Application(), HasAndroidInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private var appComponent: AppComponent? = null
    var firebaseAnalytics: FirebaseAnalytics? = null

    override fun onCreate() {
        super.onCreate()

        firebaseAnalytics = Firebase.analytics
        if (BuildConfig.FLAVOR == "prod") {
            // only enable in production mode
            firebaseAnalytics?.setAnalyticsCollectionEnabled(true)
        } else {
            firebaseAnalytics?.setAnalyticsCollectionEnabled(false)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashlyticsTree())
        }
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(BuildConfig.ENABLE_CRASH_REPORT)

        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .networkModule(NetworkModule())
            .build()

        appComponent?.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
