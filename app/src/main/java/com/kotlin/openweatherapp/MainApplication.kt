package com.kotlin.openweatherapp

import android.app.Application
import com.kotlin.openweatherapp.data.db.AppDatabase
import com.kotlin.openweatherapp.data.repository.WeatherRepository
import com.kotlin.openweatherapp.utils.Prefs

class MainApplication : Application() {
    private val database by lazy { AppDatabase.getInstance(this) }
    val repository by lazy { WeatherRepository(database.weatherDao()) }

    companion object {
        private lateinit var instance: MainApplication
        @JvmStatic
        fun getInstance() = instance
        lateinit var prefs: Prefs
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        prefs = Prefs(this)
    }

}