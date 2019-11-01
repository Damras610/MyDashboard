package com.example.mydashboard.login

import androidx.lifecycle.MutableLiveData

enum class AuthenticationState {
    UNAUTHENTICATED,
    AUTHENTICATED,
    AUTHENTICATE_FAILED
}

data class LoginUserData(
    val authState: MutableLiveData<AuthenticationState> = MutableLiveData(AuthenticationState.UNAUTHENTICATED),
    val username: MutableLiveData<String> = MutableLiveData("")
)