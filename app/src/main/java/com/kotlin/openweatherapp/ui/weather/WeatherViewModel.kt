package com.kotlin.openweatherapp.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.openweatherapp.data.db.model.Weather
import com.kotlin.openweatherapp.data.repository.WeatherRepository

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel()  {

    fun syncWeather(city: String, id: Int? = null) {
        repository.syncWeather(city, id)
    }

    fun deleteWeather(weather: Weather) {
        repository.deleteWeather(weather)
    }

    fun getWeathers(): LiveData<List<Weather>> = repository.getWeathers()
}