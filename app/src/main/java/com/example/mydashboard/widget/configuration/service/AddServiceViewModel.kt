package com.example.mydashboard.widget.configuration.service

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydashboard.widget.description.ServiceDescription
import javax.inject.Inject

class AddServiceViewModel @Inject constructor(
    _services: Array<ServiceDescription>
): ViewModel() {

    val services: MutableLiveData<Array<ServiceDescription>>
    init {
        services = MutableLiveData(_services)
    }
}