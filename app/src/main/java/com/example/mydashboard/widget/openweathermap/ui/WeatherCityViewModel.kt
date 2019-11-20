package com.example.mydashboard.widget.openweathermap.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydashboard.R
import com.example.mydashboard.widget.openweathermap.repository.OpenWeatherMapRepository
import com.example.mydashboard.widget.openweathermap.repository.WeatherCity
import javax.inject.Inject

class WeatherCityViewModel @Inject constructor(
    val openWeatherMapRepository: OpenWeatherMapRepository
) : ViewModel() {

    lateinit var widgetParams: Map<String, String>

    val weatherCity: MutableLiveData<WeatherCity>

    init {
        weatherCity = MutableLiveData()
    }

    fun setWidgetParameters(_widgetParams: Map<String, String>){
        widgetParams = _widgetParams
    }

    fun loadData(context: Context) {
        val cityName = widgetParams[context.getString(R.string.db_param_weather_city_city)] ?: return
        weatherCity.value = openWeatherMapRepository.getWeatherByCityName(cityName)
    }

}