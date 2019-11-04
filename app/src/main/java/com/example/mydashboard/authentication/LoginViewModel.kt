package com.example.mydashboard.authentication

import androidx.lifecycle.ViewModel
import com.example.mydashboard.model.user.UserRepository
import java.security.MessageDigest
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    val loginUserData: LoginUserData,
    private val userRepository: UserRepository
) : ViewModel() {

    enum class AuthenticationError {
        NO_ERROR,
        INVALID_USERNAME,
        INVALID_PASSWORD,
        UNKNOWN_USERNAME,
        WRONG_PASSWORD
    }

    // Data
    var authError : AuthenticationError

    init {
        authError = AuthenticationError.NO_ERROR
    }

    fun authenticate(username: String, password: String) {
        if (!checkUsername(username)) {
            authError = AuthenticationError.INVALID_USERNAME
            loginUserData.authState.value = AuthenticationState.AUTHENTICATE_FAILED
        }
        else if (!checkPassword(password)) {
            authError = AuthenticationError.INVALID_PASSWORD
            loginUserData.authState.value = AuthenticationState.AUTHENTICATE_FAILED
        }
        else if (validateCredentials(username, password)) {
            loginUserData.username.value = username
            authError = AuthenticationError.NO_ERROR
            loginUserData.authState.value = AuthenticationState.AUTHENTICATED
        } else {
            loginUserData.username.value = ""
            if (validateUsername(username)) {
                authError = AuthenticationError.WRONG_PASSWORD
            } else {
                authError = AuthenticationError.UNKNOWN_USERNAME
            }
            loginUserData.authState.value = AuthenticationState.AUTHENTICATE_FAILED
        }
    }

    private fun validateCredentials(username: String, password: String): Boolean {
        val hashedPassword = String(MessageDigest.getInstance("SHA-256").digest(password.toByteArray()))
        val user = userRepository.getUserByCredential(username, hashedPassword)
        return (user != null)
    }

    private fun validateUsername(username: String) : Boolean {
        val user = userRepository.getUser(username)
        return (user != null)
    }

    private fun checkUsername(username: String) : Boolean {
        return (username.length >= 5)
    }

    private fun checkPassword(password: String) : Boolean {
        return (password.length >= 5)
    }

}