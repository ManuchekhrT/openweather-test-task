package com.kotlin.openweatherapp.utils

import android.content.Context
import android.content.SharedPreferences

class Prefs(
    private val context: Context
) {

    private fun getSharedPreferences(tag: String): SharedPreferences =
        context.getSharedPreferences(tag, Context.MODE_PRIVATE)

    private val appPrefs by lazy { getSharedPreferences(APP_DATA) }

    var isAdded: Boolean
        get() = appPrefs.getBoolean(INIT_WEATHER_DATA_ADDED, false)
        set(value) = appPrefs.edit().putBoolean(INIT_WEATHER_DATA_ADDED, value).apply()

    companion object {
        private const val INIT_WEATHER_DATA_ADDED = "INIT_WEATHER_DATA_ADDED"
        private const val APP_DATA = "APP_DATA"
    }
}