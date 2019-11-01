package com.example.mydashboard.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mydashboard.dashboard.DashboardFragment
import com.example.mydashboard.dashboard.DashboardViewModel
import com.example.mydashboard.login.LoginUserData
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [
    DashboardModule.ProvideViewModel::class
])
abstract class DashboardModule {

    @ContributesAndroidInjector(modules = [
        InjectViewModel::class
    ])
    abstract fun bind(): DashboardFragment

    @Module
    class InjectViewModel {
        @Provides
        fun provideDashboardViewModel(factory: ViewModelProvider.Factory, target: DashboardFragment) : DashboardViewModel {
            return ViewModelProviders.of(target, factory).get(DashboardViewModel::class.java)
        }

    }

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(DashboardViewModel::class)
        fun provideDashboardViewModel(loginUserData: LoginUserData) : ViewModel {
            return DashboardViewModel(loginUserData)
        }
    }
}