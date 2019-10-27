package com.example.mydashboard.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydashboard.model.Authentication

class DashboardViewModel : ViewModel() {

    val authentication = MutableLiveData<Authentication>()

}