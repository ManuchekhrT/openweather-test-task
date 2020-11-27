package com.kotlin.openweatherapp.networking.api

import com.kotlin.openweatherapp.networking.response.WeatherResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequests {

    @GET(ApiEndpoints.API_FIND_CITY_WEATHER)
    fun getWeatherAsync(
        @Query(ApiConstants.CITY_PARAM) cityName: String,
        @Query(ApiConstants.UNITS_PARAM) units: String = ApiConstants.METRIC,
        @Query(ApiConstants.API_KEY_PARAM) appid: String = ApiConstants.APP_ID
    ): Deferred<Response<WeatherResponse>>




}