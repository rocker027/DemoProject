package com.coors.demoproject

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import logcat.AndroidLogcatLogger
import logcat.LogPriority


@HiltAndroidApp
class DemoApplication : Application(){

//    @Inject
//    lateinit var networkManager: NetworkManager

    override fun onCreate() {
        super.onCreate()
        AndroidLogcatLogger.installOnDebuggableApp(this, minPriority = LogPriority.VERBOSE)
    }
}