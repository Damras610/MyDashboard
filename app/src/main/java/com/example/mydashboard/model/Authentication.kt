package com.example.mydashboard.model

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