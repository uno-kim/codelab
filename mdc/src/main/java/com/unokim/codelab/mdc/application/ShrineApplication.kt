package com.unokim.codelab.mdc.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class ShrineApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    companion object {
        lateinit var instance: ShrineApplication
            private set
    }
}