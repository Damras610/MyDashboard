package com.example.mydashboard.authentication.model.logindata

import androidx.lifecycle.MutableLiveData

data class LoginUserData(
    val authState: MutableLiveData<AuthenticationState> = MutableLiveData(
        AuthenticationState.UNAUTHENTICATED
    ),
    var username: String = ""
)