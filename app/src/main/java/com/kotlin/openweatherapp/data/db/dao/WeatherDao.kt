package com.kotlin.openweatherapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kotlin.openweatherapp.data.db.model.Weather

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather WHERE id=:id ")
    fun getWeatherById(id: Int?): Weather?

    @Query("SELECT * FROM weather WHERE city=:city ")
    fun getWeatherByCity(city: String?): Weather?


    @Query("SELECT * FROM weather")
    fun getWeathers(): LiveData<List<Weather>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weather: Weather)

    @Query("DELETE FROM weather")
    fun deleteAll()

    @Update
    fun update(weather: Weather)

    @Query("UPDATE weather SET city = :city, `temp` = :temp, temp_min=:tempMin, temp_max=:tempMax, pressure=:pressure, humidity=:humidity WHERE id =:id")
    fun update(
        id: Int?,
        city: String?,
        temp: Double?,
        tempMin: Double?,
        tempMax: Double?,
        pressure: Double?,
        humidity: Int?
    )

    @Delete
    fun delete(weather: Weather)

    @Query("SELECT COUNT(*) FROM weather")
    fun getCount(): Int

}