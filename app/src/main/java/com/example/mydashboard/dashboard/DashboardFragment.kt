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
import com.example.mydashboard.login.AuthenticationState
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class DashboardFragment : Fragment() {

    // ViewModel
    @Inject
    lateinit var viewModel : DashboardViewModel

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
        viewModel.loginUserData.authState.observe(this, Observer {authState ->
            when(authState) {
                // Navigate to the splash fragment if the user is not connected
                AuthenticationState.UNAUTHENTICATED -> navController.navigate(R.id.action_dashboardFragment_to_splashFragment)
                // TODO Implement widgets
                AuthenticationState.AUTHENTICATED -> showWelcomeMessage(viewModel.loginUserData.username.value)
                else -> {}
            }
        })
    }

    override fun onResume() {
        super.onResume()
        requireActivity().findViewById<View>(R.id.toolbar)?.visibility = View.GONE
    }

    // TODO Remove this function
    private fun showWelcomeMessage(username: String?) {
        welcomeTextView.text = "Hello"
        welcomeTextView.append(" $username")
    }
}
