package com.example.mydashboard.login

enum class RegistrationStatus {
    NOT_REGISTERED,
    REGISTERED
}

enum class RegistrationError {
    NO_ERROR,
    INVALID_USERNAME,
    INVALID_PASSWORD,
    INVALID_EMAIL,
    ALREADY_USED_USERNAME
}

data class Registration (
    val registrationStatus: RegistrationStatus,
    val username: String
)