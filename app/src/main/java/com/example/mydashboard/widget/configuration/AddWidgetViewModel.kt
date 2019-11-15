package com.example.mydashboard.widget.configuration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydashboard.widget.ServiceDescription
import com.example.mydashboard.widget.WidgetDescription
import javax.inject.Inject

class AddWidgetViewModel @Inject constructor(
    _services: Array<ServiceDescription>
) : ViewModel() {

    val services = _services
    val widgets: MutableLiveData<Array<WidgetDescription>>
    init {
        widgets = MutableLiveData(emptyArray())
    }

    fun loadWidgetsFromService(serviceId: Int) {
        widgets.value = services[serviceId].widgets
    }
}