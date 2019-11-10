package com.example.mydashboard.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mydashboard.widget.AddServiceFragment
import com.example.mydashboard.widget.AddServiceViewModel
import com.example.mydashboard.widget.Services
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [
    AddServiceModule.ProvideViewModel::class
])
abstract class AddServiceModule {

    @ContributesAndroidInjector(modules = [
        InjectViewModel::class
    ])
    abstract fun bind(): AddServiceFragment

    @Module
    class InjectViewModel {
        @Provides
        fun provideAddServiceViewModel(factory: ViewModelProvider.Factory, target: AddServiceFragment) : AddServiceViewModel {
            return ViewModelProviders.of(target, factory).get(AddServiceViewModel::class.java)
        }

    }

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(AddServiceViewModel::class)
        fun provideAddServiceViewModel(services: Services) : ViewModel {
            return AddServiceViewModel(services)
        }
    }
}