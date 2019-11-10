package com.example.mydashboard.widget.openweathermap

import com.example.mydashboard.R
import com.example.mydashboard.widget.WidgetParamController

class WidgetWeatherCityParamController : WidgetParamController {

    override val paramsResId: Array<Int>

    init {
        paramsResId = arrayOf(R.string.widget_weather_city_params_city_name)
    }

    override fun checkParameters(vararg ts: String) : Boolean {
        return true
    }
}