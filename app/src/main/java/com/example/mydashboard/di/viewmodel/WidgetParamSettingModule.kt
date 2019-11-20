package com.example.mydashboard.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mydashboard.widget.SERVICES
import com.example.mydashboard.widget.configuration.widget.WidgetParamSettingFragment
import com.example.mydashboard.widget.configuration.widget.WidgetParamSettingViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [
    WidgetParamSettingModule.ProvideViewModel::class
])
abstract class WidgetParamSettingModule {

    @ContributesAndroidInjector(modules = [
        InjectViewModel::class
    ])
    abstract fun bind(): WidgetParamSettingFragment

    @Module
    class InjectViewModel {
        @Provides
        fun provideWidgetParamSettingViewModel(factory: ViewModelProvider.Factory, target: WidgetParamSettingFragment) : WidgetParamSettingViewModel {
            return ViewModelProviders.of(target, factory).get(WidgetParamSettingViewModel::class.java)
        }

    }

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(WidgetParamSettingViewModel::class)
        fun provideWidgetParamSettingViewModel() : ViewModel {
            return WidgetParamSettingViewModel(SERVICES)
        }
    }
}