package com.kotlin.openweatherapp.extensions

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.kotlin.openweatherapp.R
import com.kotlin.openweatherapp.networking.api.ApiClient
import com.kotlin.openweatherapp.networking.api.ApiRequests

fun Context.displayExceptionErrorToast() {
    Toast.makeText(this, resources.getString(R.string.error_exception), Toast.LENGTH_LONG).show()
}

fun Context.displayToast(msg: String) {
    Handler(Looper.getMainLooper()).post {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}

val apiService: ApiRequests = ApiClient.getApiClient.create(
    ApiRequests::class.java
)
