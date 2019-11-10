package com.example.mydashboard.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydashboard.authentication.logindata.AuthenticationState
import com.example.mydashboard.authentication.logindata.LoginUserData
import com.example.mydashboard.model.widget.Widget
import com.example.mydashboard.model.widget.WidgetRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    val loginUserData: LoginUserData,
    private val widgetRepository: WidgetRepository
) : ViewModel() {

    var widgets : MutableLiveData<Array<Widget>>

    init {
        widgets = MutableLiveData(emptyArray())
    }

    fun loadWidgets() {
        if (loginUserData.authState.value != AuthenticationState.AUTHENTICATED) {
            widgets.value = emptyArray()
            return
        }
        val username = loginUserData.username.value ?: run {
            widgets.value = emptyArray()
            return
        }
        widgets.value = widgetRepository.getWidgetFromUser(username)
    }

}