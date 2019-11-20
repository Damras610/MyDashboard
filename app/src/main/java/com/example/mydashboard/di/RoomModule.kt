package com.example.mydashboard.di

import android.content.Context
import androidx.room.Room
import com.example.mydashboard.authentication.model.user.UserDao
import com.example.mydashboard.authentication.model.user.UserRepository
import com.example.mydashboard.db.AppDatabase
import com.example.mydashboard.home.model.WidgetDao
import com.example.mydashboard.home.model.WidgetRepository
import com.example.mydashboard.widget.openweathermap.repository.OpenWeatherMapRepository
import com.example.mydashboard.widget.openweathermap.repository.WeatherCityDao
import com.example.mydashboard.widget.openweathermap.www.OpenWeatherMapWebService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context) : AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "Dashboard.db")
            .allowMainThreadQueries() // TODO To remove
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(appDatabase: AppDatabase) : UserDao {
        return appDatabase.userDao()
    }

    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao) : UserRepository {
        return UserRepository(userDao)
    }

    @Singleton
    @Provides
    fun provideWidgetDao(appDatabase: AppDatabase) : WidgetDao {
        return appDatabase.widgetDao()
    }

    @Singleton
    @Provides
    fun provideWidgetRepository(userDao: UserDao, widgetDao: WidgetDao) : WidgetRepository {
        return WidgetRepository(userDao, widgetDao)
    }

    @Singleton
    @Provides
    fun provideWeatherCityDao(appDatabase: AppDatabase) : WeatherCityDao {
        return appDatabase.weatherCityDao()
    }

    @Singleton
    @Provides
    fun provideOpenWeatherMapRepository(weatherCityDao: WeatherCityDao, openWeatherMapWebService: OpenWeatherMapWebService) : OpenWeatherMapRepository {
        return OpenWeatherMapRepository(weatherCityDao, openWeatherMapWebService)
    }

}