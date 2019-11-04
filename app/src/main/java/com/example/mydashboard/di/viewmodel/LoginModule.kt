package com.example.mydashboard.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mydashboard.authentication.LoginFragment
import com.example.mydashboard.authentication.LoginUserData
import com.example.mydashboard.authentication.LoginViewModel
import com.example.mydashboard.model.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module(includes = [
    LoginModule.ProvideViewModel::class,
    LoginModule.UserDataModule::class
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
        fun provideLoginViewModel(loginUserData: LoginUserData, userRepository: UserRepository) : ViewModel {
            return LoginViewModel(loginUserData, userRepository)
        }
    }

    @Module
    class UserDataModule {

        @Singleton
        @Provides
        fun provideLoginUserData() : LoginUserData {
            return LoginUserData()
        }

    }
}