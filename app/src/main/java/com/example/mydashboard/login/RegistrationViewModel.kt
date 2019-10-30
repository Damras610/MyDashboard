package com.example.mydashboard.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mydashboard.model.user.UserRepository
import javax.inject.Inject
import javax.inject.Provider

class RegistrationViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    // Data
    val registration = MutableLiveData<Registration>()
    val registrationError = MutableLiveData<RegistrationError>()

    init {
        registration.value = Registration(RegistrationStatus.NOT_REGISTERED, "")
        registrationError.value = RegistrationError.NO_ERROR
    }

    fun registerAccount(username: String, password: String, email: String) {

    }


    class Factory(val provider: Provider<RegistrationViewModel>) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return provider.get() as T
        }
    }
}