package com.example.mydashboard.splash

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor() : ViewModel() {

    val splashOver = MutableLiveData<Boolean>()

    init {
        splashOver.value = false
    }

    fun startTimer() {
        val countDownTimer = object : CountDownTimer(3000, 3000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                splashOver.value = true
            }
        }
        countDownTimer.start()
    }

}