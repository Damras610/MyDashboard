package com.example.mydashboard.widget.model.storage

import androidx.lifecycle.MutableLiveData

data class WidgetToStoreData(
    val storageState: MutableLiveData<WidgetStorageState> = MutableLiveData(
        WidgetStorageState.IDLE
    ),
    var serviceName: String = "",
    var widgetName: String = "",
    var params: Map<String, String> = emptyMap()
)