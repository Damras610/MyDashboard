package com.example.mydashboard.widget.openweathermap

import com.example.mydashboard.R
import com.example.mydashboard.widget.description.ServiceDescription
import com.example.mydashboard.widget.description.WidgetDescription

val SERVICE_DESCRIPTION = ServiceDescription(
    nameResId = R.string.service_weather_name,
    imageResId = R.drawable.im_service_weather,
    descriptionResId = R.string.service_weather_description,
    widgets = arrayOf(
        WidgetDescription(
            nameResId = R.string.widget_weather_city_name,
            descriptionResId = R.string.widget_weather_city_description,
            widgetParamController = WidgetWeatherCityParamController(),
            dbNameResId = R.string.db_widget_weather_city
        )
    ),
    dbNameResId = R.string.db_service_weather
)