package com.example.mydashboard.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mydashboard.widget.configuration.WidgetParamSettingFragment
import com.example.mydashboard.widget.configuration.WidgetParamSettingViewModel
import com.example.mydashboard.widget.description.SERVICES
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [
    WidgetParamSettingViewModule.ProvideViewModel::class
])
abstract class WidgetParamSettingViewModule {

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