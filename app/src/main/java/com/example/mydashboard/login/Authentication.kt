package com.example.mydashboard.login

enum class AuthenticationStatus {
    UNAUTHENTICATED,
    AUTHENTICATED,
}

enum class AuthenticationError {
    NO_ERROR,
    INVALID_USERNAME,
    INVALID_PASSWORD
}

data class Authentication (
    val status: AuthenticationStatus = AuthenticationStatus.UNAUTHENTICATED,
    val username: String = ""
)