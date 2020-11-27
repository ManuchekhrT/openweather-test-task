package com.kotlin.openweatherapp.data.repository

import androidx.lifecycle.LiveData
import com.kotlin.openweatherapp.MainApplication
import com.kotlin.openweatherapp.data.db.dao.WeatherDao
import com.kotlin.openweatherapp.data.db.model.Weather
import com.kotlin.openweatherapp.data.db.model.WeatherDetails
import com.kotlin.openweatherapp.extensions.apiService
import com.kotlin.openweatherapp.extensions.coroutineExceptionHandler
import com.kotlin.openweatherapp.extensions.displayToast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherRepository(private val weatherDao: WeatherDao) {

    fun getWeathers(): LiveData<List<Weather>> {
        return weatherDao.getWeathers()
    }


    fun syncWeather(city: String, id: Int?) {
        GlobalScope.launch(coroutineExceptionHandler) {
            val response = apiService.getWeatherAsync(city).await()
            if (response.isSuccessful) {
                response.body()?.let { resp ->
                    if (weatherDao.getWeatherById(id) == null) {
                        weatherDao.insert(
                            weather = Weather(
                                city = resp.name,
                                details = WeatherDetails(
                                    resp.main?.temp,

                                    resp.main?.tempMin,
                                    resp.main?.tempMax,
                                    resp.main?.pressure,
                                    resp.main?.humidity
                                )
                            )
                        )
                    } else {
                        weatherDao.update(
                            id,
                            resp.name,
                            resp.main?.temp,
                            resp.main?.tempMin,
                            resp.main?.tempMax,
                            resp.main?.pressure,
                            resp.main?.humidity
                        )

                    }
                }
            } else {
                MainApplication.getInstance()
                    .displayToast("Ooops, no weather found for this written city")
            }
        }
    }

    fun deleteWeather(weather: Weather) {
        GlobalScope.launch(coroutineExceptionHandler) {
            weatherDao.delete(weather)
        }
    }


}