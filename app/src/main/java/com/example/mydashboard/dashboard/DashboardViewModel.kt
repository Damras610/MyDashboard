package com.example.mydashboard.dashboard

import androidx.lifecycle.ViewModel
import com.example.mydashboard.authentication.AuthenticationState
import com.example.mydashboard.authentication.LoginUserData
import com.example.mydashboard.model.widget.Widget
import com.example.mydashboard.model.widget.WidgetRepository
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    val loginUserData: LoginUserData,
    val widgetRepository: WidgetRepository
) : ViewModel() {

    var widgets : Array<Widget>

    init {
        widgets = emptyArray()
    }

    fun loadWidgets() {
        if (loginUserData.authState.value != AuthenticationState.AUTHENTICATED) {
            widgets = emptyArray()
            return
        }
        val username = loginUserData.username.value ?: run {
            widgets = emptyArray()
            return
        }
        widgets = widgetRepository.getWidgetFromUser(username)
    }

}