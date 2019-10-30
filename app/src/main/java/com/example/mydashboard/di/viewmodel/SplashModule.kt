package com.example.mydashboard.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mydashboard.splash.SplashFragment
import com.example.mydashboard.splash.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [
    SplashModule.ProvideViewModel::class
])
abstract class SplashModule {

    @ContributesAndroidInjector(modules = [
        InjectViewModel::class
    ])
    abstract fun bind(): SplashFragment

    @Module
    class InjectViewModel {
        @Provides
        fun provideSplashViewModel(factory: ViewModelProvider.Factory, target: SplashFragment) : SplashViewModel {
            return ViewModelProviders.of(target, factory).get(SplashViewModel::class.java)
        }
    }

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(SplashViewModel::class)
        fun provideSplashViewModel() : ViewModel {
            return SplashViewModel()
        }
    }
}