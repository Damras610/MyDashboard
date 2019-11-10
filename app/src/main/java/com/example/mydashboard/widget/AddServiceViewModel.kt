package com.example.mydashboard.widget

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AddServiceViewModel @Inject constructor(
    _services: Services
) : ViewModel() {

    val services: MutableLiveData<Array<ServiceDescription>>
    init {
        services = MutableLiveData(_services.all)
    }
}