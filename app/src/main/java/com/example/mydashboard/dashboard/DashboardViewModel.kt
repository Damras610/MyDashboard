package com.example.mydashboard.dashboard

import androidx.lifecycle.ViewModel
import com.example.mydashboard.login.LoginUserData
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    val loginUserData: LoginUserData
) : ViewModel() {

}