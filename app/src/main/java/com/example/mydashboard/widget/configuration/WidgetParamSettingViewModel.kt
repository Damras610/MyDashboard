package com.example.mydashboard.widget.configuration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydashboard.widget.BaseParamController
import com.example.mydashboard.widget.ServiceDescription
import com.example.mydashboard.widget.WidgetParamController
import javax.inject.Inject

class WidgetParamSettingViewModel @Inject constructor(
    _services: Array<ServiceDescription>
) : ViewModel() {

    val services = _services
    val paramController: MutableLiveData<WidgetParamController>
    init {
        paramController = MutableLiveData(BaseParamController())
    }

    fun loadParamController(serviceId: Int, widgetId: Int) {
        paramController.value = services[serviceId].widgets[widgetId].widgetParamController
    }
}