package com.example.mydashboard.widget

import android.content.Context
import androidx.core.os.bundleOf
import com.example.mydashboard.R
import com.example.mydashboard.home.model.Widget
import com.example.mydashboard.widget.base.ui.BaseWidgetFragment
import com.example.mydashboard.widget.openweathermap.ui.WeatherCityFragment

class WidgetFragmentFactory(
    val context: Context
) {

    fun create(widget: Widget) : BaseWidgetFragment {
        val fragment = when (widget.serviceName) {
            context.getString(R.string.db_service_weather) -> createWeatherFragment(widget)
            else -> BaseWidgetFragment(false, widget.serviceName, widget.widgetName)
        }

        fragment.arguments = bundleOf(BaseWidgetFragment.ARGUMENT_NAME to widget.parameters)
        return fragment
    }

    private fun createWeatherFragment(widget: Widget) : BaseWidgetFragment {
        return when (widget.widgetName) {
            context.getString(R.string.db_widget_weather_city) -> WeatherCityFragment()
            else -> BaseWidgetFragment(false, widget.serviceName, widget.widgetName)
        }
    }
}