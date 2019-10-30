package com.example.mydashboard.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mydashboard.login.LoginFragment
import com.example.mydashboard.login.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [
    LoginModule.ProvideViewModel::class
])
abstract class LoginModule {

    @ContributesAndroidInjector(modules = [
        InjectViewModel::class
    ])
    abstract fun bind(): LoginFragment

    @Module
    class InjectViewModel {
        @Provides
        fun provideLoginViewModel(factory: ViewModelProvider.Factory, target: LoginFragment) : LoginViewModel {
            return ViewModelProviders.of(target, factory).get(LoginViewModel::class.java)
        }

    }

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(LoginViewModel::class)
        fun provideLoginViewModel() : ViewModel {
            return LoginViewModel()
        }
    }
}