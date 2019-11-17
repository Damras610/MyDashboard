package com.example.mydashboard.widget.configuration

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydashboard.authentication.logindata.LoginUserData
import com.example.mydashboard.model.widget.WidgetRepository
import com.example.mydashboard.widget.ServiceDescription
import com.example.mydashboard.widget.WidgetDescription
import javax.inject.Inject

class AddWidgetViewModel @Inject constructor(
    _services: Array<ServiceDescription>,
    val widgetRepository: WidgetRepository,
    val loginUserData: LoginUserData
) : ViewModel() {

    val services = _services
    val widgets: MutableLiveData<Array<WidgetDescription>>
    init {
        widgets = MutableLiveData(emptyArray())
    }

    fun loadWidgetsFromService(serviceId: Int) {
        widgets.value = services[serviceId].widgets
    }

    fun addWidget(context: Context, serviceId: Int, widgetId: Int) {
        val username = loginUserData.username.value ?: return
        widgetRepository.addWidgetToUser(
            username,
            context.getString(services[serviceId].dbNameResId),
            context.getString(services[serviceId].widgets[widgetId].dbNameResId))
    }
}