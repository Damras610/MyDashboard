package com.example.mydashboard.widget

class Services {

    val OPEN_WEATHER_MAP_DESCRIPTION = com.example.mydashboard.widget.openweathermap.SERVICE_DESCRIPTION

    val all : Array<ServiceDescription>
    init {
        all = arrayOf(
            OPEN_WEATHER_MAP_DESCRIPTION,
            OPEN_WEATHER_MAP_DESCRIPTION,
            OPEN_WEATHER_MAP_DESCRIPTION,
            OPEN_WEATHER_MAP_DESCRIPTION,
            OPEN_WEATHER_MAP_DESCRIPTION,
            OPEN_WEATHER_MAP_DESCRIPTION,
            OPEN_WEATHER_MAP_DESCRIPTION
        )
    }
}
