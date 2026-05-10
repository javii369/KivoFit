package com.KivoFit

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "API_BASE_URL=${BuildConfig.API_BASE_URL}")
        }
    }

    companion object {
        private const val TAG = "KivoFit"
    }
}
