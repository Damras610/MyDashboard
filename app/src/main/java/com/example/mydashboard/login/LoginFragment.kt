package com.example.mydashboard.login

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mydashboard.R


class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onStart() {
        super.onStart()
        val countDownTimer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
            }
        }
        countDownTimer.start()
    }

    override fun onResume() {
        super.onResume()
        activity?.findViewById<Toolbar>(R.id.toolbar)?.visibility = View.GONE
    }
}
