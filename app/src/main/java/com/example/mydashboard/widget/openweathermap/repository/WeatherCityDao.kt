package com.example.mydashboard.widget.openweathermap.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeatherCityDao {

    @Query("SELECT * FROM weather_city WHERE city = :cityName ORDER BY datetime DESC LIMIT 1")
    fun getWeatherFromCityName(cityName: String) : WeatherCity?

    @Insert
    fun insertWeatherCity(weatherCity: WeatherCity) : Long
}