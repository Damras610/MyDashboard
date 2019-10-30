package com.example.mydashboard.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {

    // Data
    val authentication = MutableLiveData<Authentication>()
    val authenticationError = MutableLiveData<AuthenticationError>()

    init {
        authentication.value = Authentication(AuthenticationStatus.UNAUTHENTICATED,"")
        authenticationError.value = AuthenticationError.NO_ERROR
    }

    fun authenticate(username: String, password: String) {
        if (validateCredentials(username, password)) {
            authentication.value = Authentication(
                AuthenticationStatus.AUTHENTICATED,
                username
            )
        } else {
            authentication.value = Authentication(
                AuthenticationStatus.UNAUTHENTICATED,
                ""
            )
        }
    }

    private fun validateCredentials(username: String, password: String): Boolean {
        authenticationError.value = AuthenticationError.INVALID_USERNAME
        return false
    }

}