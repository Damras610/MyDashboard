package com.example.mydashboard.widget.configuration

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydashboard.widget.description.ServiceDescription
import com.example.mydashboard.widget.description.WidgetDescription
import com.example.mydashboard.widget.description.WidgetParamDescription
import com.example.mydashboard.widget.model.WidgetStorageState
import com.example.mydashboard.widget.model.WidgetToStoreData
import javax.inject.Inject

class AddWidgetViewModel @Inject constructor(
    _services: Array<ServiceDescription>,
    val widgetToStoreData: WidgetToStoreData
) : ViewModel() {

    val services = _services
    val widgets: MutableLiveData<Array<WidgetDescription>>
    init {
        widgets = MutableLiveData(emptyArray())
    }

    fun loadWidgetsFromService(serviceId: Int) {
        widgets.value = services[serviceId].widgets
    }

    fun addWidget(context: Context, serviceId: Int, widgetId: Int, paramsValue: Map<WidgetParamDescription, String>) {
        val params = mutableMapOf<String, String>()
        paramsValue.forEach {entry ->
            params[context.getString(entry.key.dbNameResId)] = entry.value
        }

        widgetToStoreData.serviceName = context.getString(services[serviceId].dbNameResId)
        widgetToStoreData.widgetName = context.getString(services[serviceId].widgets[widgetId].dbNameResId)
        widgetToStoreData.params = params
        widgetToStoreData.storageState.value = WidgetStorageState.TO_STORE
    }
}