package com.example.mydashboard.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mydashboard.R
import com.example.mydashboard.login.AuthenticationStatus
import com.example.mydashboard.login.LoginViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class DashboardFragment : Fragment() {

    // ViewModels
    @Inject
    lateinit var loginViewModel: LoginViewModel

    // Views
    private lateinit var welcomeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        // Init the views
        welcomeTextView = view.findViewById(R.id.welcome_tv)

        // Observe models
        loginViewModel.authentication.observe(this, Observer {authentication ->
            when(authentication.status) {
                // Navigate to the splash fragment if the user is not connected
                AuthenticationStatus.UNAUTHENTICATED -> navController.navigate(R.id.action_dashboardFragment_to_splashFragment)
                // TODO Implement widgets
                AuthenticationStatus.AUTHENTICATED -> showWelcomeMessage(authentication.username)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        requireActivity().findViewById<View>(R.id.toolbar)?.visibility = View.GONE
    }

    // TODO Remove this function
    private fun showWelcomeMessage(username: String) {
        welcomeTextView.append(" $username")
    }


}
