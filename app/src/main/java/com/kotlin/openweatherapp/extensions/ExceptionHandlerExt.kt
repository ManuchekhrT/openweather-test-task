package com.kotlin.openweatherapp.extensions

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.kotlin.openweatherapp.BuildConfig
import com.kotlin.openweatherapp.MainApplication
import kotlinx.coroutines.CoroutineExceptionHandler

val coroutineExceptionHandler by lazy {
    CoroutineExceptionHandler { _, t ->
        if (BuildConfig.DEBUG)
            Log.d("EXCEPTION", "" + t.message)

        Handler(Looper.getMainLooper()).post {
            MainApplication.getInstance().applicationContext.displayExceptionErrorToast()
        }

    }
}