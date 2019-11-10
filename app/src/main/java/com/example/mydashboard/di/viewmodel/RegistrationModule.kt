package com.example.mydashboard.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mydashboard.authentication.RegistrationFragment
import com.example.mydashboard.authentication.RegistrationViewModel
import com.example.mydashboard.authentication.logindata.LoginUserData
import com.example.mydashboard.model.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [
    RegistrationModule.ProvideViewModel::class
])
abstract class RegistrationModule {

    @ContributesAndroidInjector(modules = [
        InjectViewModel::class
    ])
    abstract fun bind(): RegistrationFragment

    @Module
    class InjectViewModel {
        @Provides
        fun provideRegistrationViewModel(factory: ViewModelProvider.Factory, target: RegistrationFragment) : RegistrationViewModel {
            return ViewModelProviders.of(target, factory).get(RegistrationViewModel::class.java)
        }
    }

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(RegistrationViewModel::class)
        fun provideRegistrationViewModel(loginUserData: LoginUserData, userRepository: UserRepository) : ViewModel {
            return RegistrationViewModel(loginUserData, userRepository)
        }
    }
}