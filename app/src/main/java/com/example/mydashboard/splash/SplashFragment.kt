package com.example.mydashboard.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.mydashboard.R

class SplashFragment: Fragment() {

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the ViewModel
        viewModel = requireActivity().run {
            ViewModelProviders.of(this)[SplashViewModel::class.java]
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        // Go to the login screen once the timer is
        viewModel.splashOver.observe(this, Observer<Boolean> {splashOver ->
            if (splashOver == true) {
                navController.navigate(R.id.action_splashFragment_to_loginFragment)
            }
        })

        // Manage back button
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().findViewById<Toolbar>(R.id.toolbar)?.visibility = View.GONE
    }

    override fun onStart() {
        super.onStart()
        viewModel.startTimer()
    }
}
