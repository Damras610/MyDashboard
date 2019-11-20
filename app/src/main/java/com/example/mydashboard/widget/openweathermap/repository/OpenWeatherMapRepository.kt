package com.example.mydashboard.widget.openweathermap.repository

import com.example.mydashboard.widget.openweathermap.www.OpenWeatherMapWebService
import com.example.mydashboard.widget.openweathermap.www.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

class OpenWeatherMapRepository @Inject constructor(
    val weatherCityDao: WeatherCityDao,
    val openWeatherMapWebService: OpenWeatherMapWebService
) {
    fun getWeatherByCityName(cityName: String) : WeatherCity? {
        var weatherCity = weatherCityDao.getWeatherFromCityName(cityName)

        // If no previous data for the city is found or if the last fetch was more than 3 hours ago,
        // then refresh the data from the web service
        // otherwise, return the data fetched from the db.
        if (weatherCity == null || LocalDateTime.ofEpochSecond(weatherCity.datetime, 0, ZoneOffset.UTC).plusHours(3).isBefore(LocalDateTime.now())) {

            val call = openWeatherMapWebService.getWeatherByCityName(cityName)
            call.enqueue(object : Callback<WeatherResponse> {
                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    throw t
                    weatherCity = null
                }

                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if (response.code() == 200) {
                        val weatherResponse = response.body()!!
                        weatherCity = WeatherCity(
                            0,
                            weatherResponse.name!!,
                            weatherResponse.sys!!.country!!,
                            LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
                            weatherResponse.weather[0].icon!!,
                            weatherResponse.weather[0].main!!,
                            weatherResponse.weather[0].description!!,
                            weatherResponse.main!!.temp!!,
                            weatherResponse.main!!.temp_min!!,
                            weatherResponse.main!!.temp_max
                        )

                        weatherCity?.let {
                            weatherCityDao.insertWeatherCity(it)
                        }
                    } else {
                        Timber.d("FAILLLLL 2")
                        weatherCity == null
                    }
                }
            })

        }

        return weatherCity
    }

}