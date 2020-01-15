package com.unokim.codelab.minipaint

import android.app.Application
import timber.log.Timber

class MiniPaintApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return "uno@" + super.createStackElementTag(element)
            }
        })
    }
}