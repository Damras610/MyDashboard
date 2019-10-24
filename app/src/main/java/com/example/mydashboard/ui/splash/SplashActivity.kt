package com.example.mydashboard.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.example.mydashboard.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setInFullScreen()
        launchSplashTimer()
    }

    private fun setInFullScreen () {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN;
        actionBar?.hide()
        supportActionBar?.hide()
    }

    private fun launchSplashTimer() {
        val countDownTimer = object : CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {

            }

        }
        countDownTimer.start()
    }

}
