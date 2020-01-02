package com.unokim.codelab.logger

import android.util.Log

object Logger {
    private const val TAG = "uno@"

    fun d(tag: String, msg: String) {
        Log.d(TAG + tag, msg)
    }

    fun i(tag: String, msg: String) {
        Log.i(TAG + tag, msg)
    }

    fun e(tag: String, msg: String) {
        Log.e(TAG + tag, msg)
    }
}