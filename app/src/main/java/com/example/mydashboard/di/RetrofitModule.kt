package com.example.mydashboard.di

import android.content.Context
import com.example.mydashboard.widget.openweathermap.www.OpenWeatherMapWebService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun provideOpenWeatherMapWebService(context: Context) : OpenWeatherMapWebService {
        val retrofit = Retrofit.Builder()
            .baseUrl(OpenWeatherMapWebService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(OpenWeatherMapWebService::class.java)
    }

}