package com.kotlin.openweatherapp.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class Weather(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "city") val city: String?,
    @Embedded val details: WeatherDetails?
): Serializable

data class WeatherDetails(
    @ColumnInfo(name = "temp") val temp: Double?,
    @ColumnInfo(name = "temp_min") val tempMin: Double?,
    @ColumnInfo(name = "temp_max") val tempMax: Double?,
    @ColumnInfo(name = "pressure") val pressure: Double?,
    @ColumnInfo(name = "humidity") val humidity: Int?
): Serializable

