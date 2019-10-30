package com.example.mydashboard.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mydashboard.R
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class LoginFragment : Fragment() {

    // ViewModels
    @Inject
    lateinit var loginViewModel: LoginViewModel

    // Views
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signInButton: Button
    private lateinit var signOnButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        // Init views
        usernameEditText = view.findViewById(R.id.login_username_edit)
        passwordEditText = view.findViewById(R.id.login_password_edit)
        signInButton = view.findViewById(R.id.login_sign_in_button)
        signOnButton = view.findViewById(R.id.login_sign_on_button)

        // Observe models
        loginViewModel.authentication.observe(this, Observer { authentication ->
            if (authentication.status == AuthenticationStatus.AUTHENTICATED) {
                navController.popBackStack(R.id.dashboardFragment, false)
            }
        })

        loginViewModel.authenticationError.observe(this, Observer { authenticationError ->
            showErrorMessage(authenticationError)
        })

        // Init onClick listeners
        signInButton.setOnClickListener {
            val username= usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            loginViewModel.authenticate(username, password)
        }
        signOnButton.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        // Manage back button
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().findViewById<View>(R.id.toolbar)?.visibility = View.GONE
    }

    private fun showErrorMessage(authenticationError: AuthenticationError) {
        when(authenticationError) {
            AuthenticationError.INVALID_USERNAME -> run {
                usernameEditText.setError(getString(R.string.invalid_username))
                passwordEditText.setError(null)
            }
            AuthenticationError.INVALID_PASSWORD -> run {
                usernameEditText.setError(null)
                passwordEditText.setError(getString(R.string.invalid_password))
            }
            AuthenticationError.NO_ERROR -> run {
                usernameEditText.setError(null)
                passwordEditText.setError(null)
            }
        }
    }



}
