package com.example.mydashboard.widget.openweathermap.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.mydashboard.R

import com.example.mydashboard.widget.base.ui.BaseWidgetFragment
import com.example.mydashboard.widget.openweathermap.repository.WeatherCity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_widget_weather_city.view.*
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class WeatherCityFragment : BaseWidgetFragment() {

    // View Model
    @Inject
    lateinit var viewModel: WeatherCityViewModel

    // Views
    private lateinit var iconImageView: ImageView
    private lateinit var mainWeatherTextView: TextView
    private lateinit var descriptionWeatherTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var cityCountryTextView: TextView
    private lateinit var tempTextView: TextView
    private lateinit var minTempTextView: TextView
    private lateinit var maxTempTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)

        viewModel.setWidgetParameters(arguments?.get(BaseWidgetFragment.ARGUMENT_NAME) as Map<String, String>)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_widget_weather_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iconImageView = view.widget_weather_city_icon
        mainWeatherTextView = view.widget_weather_city_main_weather
        descriptionWeatherTextView = view.widget_weather_city_description_weather
        dateTextView = view.widget_weather_city_date
        cityCountryTextView = view.widget_weather_city_city_country
        tempTextView = view.widget_weather_city_temp
        minTempTextView = view.widget_weather_city_min_temp
        maxTempTextView = view.widget_weather_city_max_temp

        viewModel.weatherCity.observe(this, Observer {weatherCity ->
            if (weatherCity != null) {
                updateView(weatherCity)
            } else {
                displayError()
            }
        })

        viewModel.loadData(requireContext())
    }

    private fun updateView(weatherCity: WeatherCity) {
        Glide.with(this)
            .load("http://openweathermap.org/img/wn/${weatherCity.icon}@2x.png")
            .centerCrop()
            .into(iconImageView)
        mainWeatherTextView.setText(weatherCity.weather)
        descriptionWeatherTextView.setText(weatherCity.description)
        dateTextView.setText(LocalDateTime.ofEpochSecond(weatherCity.datetime, 0, ZoneOffset.UTC).format(
            DateTimeFormatter.ofPattern("MMM d, h:ma")))
        cityCountryTextView.setText("${weatherCity.city}, ${weatherCity.country}")
        tempTextView.setText("${weatherCity.temp.toInt()}°C")
        minTempTextView.setText("min ${weatherCity.tempMin.toInt()}°C")
        maxTempTextView.setText("max ${weatherCity.tempMax.toInt()}°C")
    }

    private fun displayError() {

    }
}
