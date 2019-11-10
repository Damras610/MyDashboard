package com.example.mydashboard.authentication.logindata

import androidx.lifecycle.MutableLiveData

data class LoginUserData(
    val authState: MutableLiveData<AuthenticationState> = MutableLiveData(
        AuthenticationState.UNAUTHENTICATED
    ),
    val username: MutableLiveData<String> = MutableLiveData("")
)