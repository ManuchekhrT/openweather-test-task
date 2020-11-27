package com.kotlin.openweatherapp.networking.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("cod") val cod: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("main") val main: WeatherDetails?
)

data class WeatherDetails(
    @SerializedName("temp") val temp: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("pressure") val pressure: Double,
    @SerializedName("humidity") val humidity: Int
)