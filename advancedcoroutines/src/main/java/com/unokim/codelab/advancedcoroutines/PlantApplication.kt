package com.unokim.codelab.advancedcoroutines

import android.app.Application
import timber.log.Timber

class PlantApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return "uno@" + super.createStackElementTag(element)
            }
        })
    }
}