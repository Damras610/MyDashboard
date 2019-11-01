package com.example.mydashboard.login

import androidx.lifecycle.ViewModel
import com.example.mydashboard.model.user.UserRepository
import timber.log.Timber
import java.security.MessageDigest
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    val loginUserData: LoginUserData,
    private val userRepository: UserRepository
) : ViewModel() {

    enum class AuthenticationError {
        NO_ERROR,
        INVALID_USERNAME,
        INVALID_PASSWORD
    }

    // Data
    var authError : AuthenticationError

    init {
        authError = AuthenticationError.NO_ERROR
    }

    fun authenticate(username: String, password: String) {
        Timber.d("PLOP")
        if (validateCredentials(username, password)) {
            Timber.d("Hello ICI !!")
            loginUserData.authState.value = AuthenticationState.AUTHENTICATED
            loginUserData.username.value = username
            authError = AuthenticationError.NO_ERROR
        } else {
            loginUserData.authState.value = AuthenticationState.AUTHENTICATE_FAILED
            loginUserData.username.value = ""
            if (validateUsername(username)) {
                authError = AuthenticationError.INVALID_PASSWORD
            } else {
                authError = AuthenticationError.INVALID_USERNAME
            }
        }
    }

    private fun validateCredentials(username: String, password: String): Boolean {
        val hashedPassword = MessageDigest.getInstance("SHA-1").digest(password.toByteArray()).toString()
        val user = userRepository.getUserByCredential(username, hashedPassword)
        return (true)
    }

    private fun validateUsername(username: String) : Boolean {
        val user = userRepository.getUser(username)
        return (true)
    }

}