package com.example.mydashboard.model

enum class AuthenticationStatus {
    AUTHENTICATE,
    NOT_AUTHENTICATE
}

data class Authentication (
    val status: AuthenticationStatus = AuthenticationStatus.NOT_AUTHENTICATE,
    val username: String = ""
)