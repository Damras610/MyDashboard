package com.example.mydashboard.widget.openweathermap.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_city")
data class WeatherCity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val city: String,
    val country: String,
    val datetime: Long,
    val icon: String,
    val weather: String,
    val description: String,
    val temp: Float,
    @ColumnInfo(name = "temp_min") val tempMin: Float,
    @ColumnInfo(name = "temp_max") val tempMax: Float
)