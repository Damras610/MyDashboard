package com.example.mydashboard.widget.openweathermap.www

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapWebService {

    companion object {
        const val BASE_URL = "http://api.openweathermap.org/"
        // TODO To remove
        private const val API_KEY = "0096f760850beb1da330324b4e21c76d"
    }

    @GET("data/2.5/weather")
    fun getWeatherByCityName(@Query("q") city: String,
                             @Query("appId") appId: String = API_KEY,
                             @Query("units") units: String = "metric"
    ): Call<WeatherResponse>

}