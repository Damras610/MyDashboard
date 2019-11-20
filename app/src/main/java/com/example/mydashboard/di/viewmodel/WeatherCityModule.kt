package com.example.mydashboard.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mydashboard.widget.openweathermap.repository.OpenWeatherMapRepository
import com.example.mydashboard.widget.openweathermap.ui.WeatherCityFragment
import com.example.mydashboard.widget.openweathermap.ui.WeatherCityViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [
    WeatherCityModule.ProvideViewModel::class
])
abstract class WeatherCityModule {

    @ContributesAndroidInjector(modules = [
        InjectViewModel::class
    ])
    abstract fun bind(): WeatherCityFragment

    @Module
    class InjectViewModel {
        @Provides
        fun provideWeatherCityViewModel(factory: ViewModelProvider.Factory, target: WeatherCityFragment) : WeatherCityViewModel {
            return ViewModelProviders.of(target, factory).get(WeatherCityViewModel::class.java)
        }

    }

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(WeatherCityViewModel::class)
        fun provideWeatherCityViewModel(openWeatherMapRepository: OpenWeatherMapRepository) : ViewModel {
            return WeatherCityViewModel(openWeatherMapRepository)
        }
    }
}