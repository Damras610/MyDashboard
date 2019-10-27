package com.example.mydashboard.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.mydashboard.R
import com.example.mydashboard.model.Authentication
import com.example.mydashboard.model.AuthenticationStatus


class DashboardFragment : Fragment() {

    // ViewModel
    private lateinit var viewModel: DashboardViewModel

    // Views
    private lateinit var welcomeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the ViewModel
        viewModel = activity?.run {
            ViewModelProviders.of(this)[DashboardViewModel::class.java]
        } ?: throw Exception("Cannot retrieve DashboardViewModel.")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the views
        welcomeTextView = view.findViewById(R.id.welcome_tv)

        // Navigate to the splash and login activity if the user is not connected
        viewModel.authentication.observe(this, Observer<Authentication> {
            when(it.status) {
                AuthenticationStatus.NOT_AUTHENTICATE -> findNavController().navigate(R.id.action_dashboardFragment_to_splashFragment)
                AuthenticationStatus.AUTHENTICATE     -> showWelcomeMessage(it.username)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        activity?.findViewById<Toolbar>(R.id.toolbar)?.visibility = View.VISIBLE
    }

    private fun showWelcomeMessage(username: String) {
        welcomeTextView.append(" $username")
    }
}
