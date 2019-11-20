package com.example.mydashboard.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mydashboard.di.viewmodel.*
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module(includes = [
    SplashModule::class,
    LoginModule::class,
    RegistrationModule::class,
    HomeModule::class,
    AddServiceModule::class,
    AddWidgetModule::class,
    WidgetParamSettingModule::class,
    WeatherCityModule::class
])
class ViewModelFactoryModule {

    @Singleton
    @Provides
    @Suppress("UNCHECKED_CAST")
    fun provideViewModelFactory(
        providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ) : ViewModelProvider.Factory {
       return object : ViewModelProvider.Factory {
           override fun <T : ViewModel?> create(modelClass: Class<T>): T {
               return requireNotNull(providers[modelClass as Class<out ViewModel>]).get() as T
           }
       }
    }

}