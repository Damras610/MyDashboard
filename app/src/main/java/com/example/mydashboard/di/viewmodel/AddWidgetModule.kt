package com.example.mydashboard.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mydashboard.widget.SERVICES
import com.example.mydashboard.widget.configuration.widget.AddWidgetFragment
import com.example.mydashboard.widget.configuration.widget.AddWidgetViewModel
import com.example.mydashboard.widget.model.storage.WidgetToStoreData
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module(includes = [
    AddWidgetModule.ProvideViewModel::class,
    AddWidgetModule.WidgetToStoreModule::class
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
        fun provideAddWidgetViewModel(widgetToStoreData: WidgetToStoreData) : ViewModel {
            return AddWidgetViewModel(SERVICES, widgetToStoreData)
        }
    }

    @Module
    class WidgetToStoreModule {

        @Singleton
        @Provides
        fun provideWidgetToStoreData() : WidgetToStoreData {
            return WidgetToStoreData()
        }
    }
}