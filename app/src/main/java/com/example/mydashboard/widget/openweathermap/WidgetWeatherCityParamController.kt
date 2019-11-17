package com.example.mydashboard.widget.openweathermap

import com.example.mydashboard.R
import com.example.mydashboard.widget.base.BaseParamController
import com.example.mydashboard.widget.description.WidgetParamDescription

class WidgetWeatherCityParamController : BaseParamController() {

    override val params: Array<WidgetParamDescription>
    val paramChecker: Map<WidgetParamDescription, (String) -> Boolean>

    init {
        params = arrayOf(
            WidgetParamDescription(
                R.string.widget_weather_city_params_city_name,
                R.string.db_param_weather_city_city
            )
        )

        val checkCity : (String) -> Boolean = { param -> !param.isEmpty() }

        paramChecker = mapOf(
            params[0] to checkCity
        )
    }

    override fun checkParameters(paramsValue: Map<WidgetParamDescription, String>) : Boolean {
        invalidFields = emptyArray()
        paramsValue.forEach {entry ->
            if (paramChecker[entry.key]?.invoke(entry.value) == false) {
                invalidFields = invalidFields.plus(entry.key)
            }
        }
        return invalidFields.isEmpty()
    }
}