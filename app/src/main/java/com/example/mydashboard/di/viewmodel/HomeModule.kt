package com.example.mydashboard.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mydashboard.authentication.model.logindata.LoginUserData
import com.example.mydashboard.home.HomeFragment
import com.example.mydashboard.home.HomeViewModel
import com.example.mydashboard.home.model.WidgetRepository
import com.example.mydashboard.widget.model.WidgetToStoreData
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [
    HomeModule.ProvideViewModel::class
])
abstract class HomeModule {

    @ContributesAndroidInjector(modules = [
        InjectViewModel::class
    ])
    abstract fun bind(): HomeFragment

    @Module
    class InjectViewModel {
        @Provides
        fun provideHomeViewModel(factory: ViewModelProvider.Factory, target: HomeFragment) : HomeViewModel {
            return ViewModelProviders.of(target, factory).get(HomeViewModel::class.java)
        }

    }

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(HomeViewModel::class)
        fun provideHomeViewModel(loginUserData: LoginUserData,  widgetToStoreData: WidgetToStoreData, widgetRepository: WidgetRepository) : ViewModel {
            return HomeViewModel(loginUserData, widgetToStoreData, widgetRepository)
        }
    }
}