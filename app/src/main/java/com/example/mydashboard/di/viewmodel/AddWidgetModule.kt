package com.example.mydashboard.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mydashboard.widget.SERVICES
import com.example.mydashboard.widget.configuration.AddWidgetFragment
import com.example.mydashboard.widget.configuration.AddWidgetViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [
    AddWidgetModule.ProvideViewModel::class
])
abstract class AddWidgetModule {

    @ContributesAndroidInjector(modules = [
        InjectViewModel::class
    ])
    abstract fun bind(): AddWidgetFragment

    @Module
    class InjectViewModel {
        @Provides
        fun provideAddWidgetViewModel(factory: ViewModelProvider.Factory, target: AddWidgetFragment) : AddWidgetViewModel {
            return ViewModelProviders.of(target, factory).get(AddWidgetViewModel::class.java)
        }

    }

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(AddWidgetViewModel::class)
        fun provideAddWidgetViewModel() : ViewModel {
            return AddWidgetViewModel(SERVICES)
        }
    }
}