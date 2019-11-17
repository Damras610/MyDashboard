package com.example.mydashboard.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydashboard.authentication.model.logindata.AuthenticationState
import com.example.mydashboard.authentication.model.logindata.LoginUserData
import com.example.mydashboard.home.model.Widget
import com.example.mydashboard.home.model.WidgetRepository
import com.example.mydashboard.widget.model.storage.WidgetToStoreData
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    val loginUserData: LoginUserData,
    val widgetToStoreData: WidgetToStoreData,
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
        val username = loginUserData.username
        widgets.value = widgetRepository.getWidgetFromUser(username)
    }

    fun storeWidget() {
        widgetRepository.addWidgetToUser(
            loginUserData.username,
            widgetToStoreData.serviceName,
            widgetToStoreData.widgetName,
            widgetToStoreData.params
        )
    }

}