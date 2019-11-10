package com.example.mydashboard.authentication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydashboard.authentication.logindata.AuthenticationState
import com.example.mydashboard.authentication.logindata.LoginUserData
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
        if (!checkUsername(username)) {
            regisError = RegistrationError.INVALID_USERNAME
            regisState.value = RegistrationState.REGISTRATION_FAILED
        }
        else if (!checkPassword(password)) {
            regisError = RegistrationError.INVALID_PASSWORD
            regisState.value = RegistrationState.REGISTRATION_FAILED
        }
        else if (!checkEmail(email)) {
            regisError = RegistrationError.INVALID_EMAIL
            regisState.value = RegistrationState.REGISTRATION_FAILED
        }
        else if (saveNewUser(username, password, email)) {
            regisState.value = RegistrationState.REGISTERED
            loginUserData.username.value = username
            loginUserData.authState.value = AuthenticationState.AUTHENTICATED
        } else {
            regisError = RegistrationError.ALREADY_USED_USERNAME
            regisState.value = RegistrationState.REGISTRATION_FAILED
        }
    }

    private fun saveNewUser(username: String, password: String, email: String) : Boolean {
        val hashedPassword = String(MessageDigest.getInstance("SHA-256").digest(password.toByteArray()))
        val userId = userRepository.saveNewUser(username, hashedPassword, email)
        return (userId != -1L)
    }

    private fun checkUsername(username: String) : Boolean {
        return (username.length >= 5)
    }

    private fun checkPassword(password: String) : Boolean {
        return (password.length >= 5)
    }

    private fun checkEmail(email: String) : Boolean {
        return email.isEmpty() || email.matches(Regex("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"))
    }

}