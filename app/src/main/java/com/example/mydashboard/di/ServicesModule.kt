package com.example.mydashboard.di

import com.example.mydashboard.widget.Services
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ServicesModule {

    @Singleton
    @Provides
    fun provideServices(): Services {
        return Services()
    }

}