package com.example.mydashboard.widget.configuration.widget

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydashboard.widget.base.configuration.BaseWidgetParamController
import com.example.mydashboard.widget.description.ServiceDescription
import com.example.mydashboard.widget.description.WidgetParamController
import javax.inject.Inject

class WidgetParamSettingViewModel @Inject constructor(
    _services: Array<ServiceDescription>
) : ViewModel() {

    val services = _services
    val paramController: MutableLiveData<WidgetParamController>
    init {
        paramController = MutableLiveData(BaseWidgetParamController())
    }

    fun loadParamController(serviceId: Int, widgetId: Int) {
        paramController.value = services[serviceId].widgets[widgetId].widgetParamController
    }
}