package com.example.mydashboard.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydashboard.model.user.UserRepository
import java.security.MessageDigest
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    val loginUserData: LoginUserData,
    private val userRepository: UserRepository
): ViewModel() {

    enum class RegistrationState {
        UNREGISTERED,
        REGISTERED,
        REGISTRATION_FAILED
    }

    enum class RegistrationError {
        NO_ERROR,
        INVALID_USERNAME,
        INVALID_PASSWORD,
        INVALID_EMAIL,
        ALREADY_USED_USERNAME
    }

    // Data
    val regisState : MutableLiveData<RegistrationState>
    var regisError : RegistrationError

    init {
        regisState = MutableLiveData(RegistrationState.UNREGISTERED)
        regisError = RegistrationError.NO_ERROR
    }

    fun registerAccount(username: String, password: String, email: String) {
        if (saveUser(username, password, email)) {
            regisState.value = RegistrationState.REGISTERED
            loginUserData.authState.value = AuthenticationState.AUTHENTICATED
            loginUserData.username.value = username
        } else {
            regisError = RegistrationError.ALREADY_USED_USERNAME
        }
    }

    private fun saveUser(username: String, password: String, email: String) : Boolean {
        val hashedPassword = MessageDigest.getInstance("SHA-1").digest(password.toByteArray()).toString()
        val user = userRepository.getUserByCredential(username, hashedPassword)
        return (true)
    }
}