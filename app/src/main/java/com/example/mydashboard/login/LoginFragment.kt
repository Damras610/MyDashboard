package com.example.mydashboard.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mydashboard.R
import com.google.android.material.textfield.TextInputLayout
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class LoginFragment : Fragment() {

    // ViewModel
    @Inject
    lateinit var viewModel: LoginViewModel

    // Views
    private lateinit var usernameEditText: TextInputLayout
    private lateinit var passwordEditText: TextInputLayout
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
        usernameEditText = view.findViewById(R.id.login_username_editlayout)
        passwordEditText = view.findViewById(R.id.login_password_editlayout)
        signInButton = view.findViewById(R.id.login_sign_in_button)
        signOnButton = view.findViewById(R.id.login_sign_on_button)

        // Observe models
        viewModel.loginUserData.authState.observe(this, Observer { authState ->
            when (authState) {
                AuthenticationState.AUTHENTICATED -> navController.popBackStack(R.id.dashboardFragment,false)
                AuthenticationState.AUTHENTICATE_FAILED -> showErrorMessage()
                else -> {}
            }
        })

        // Init onClick listeners
        signInButton.setOnClickListener {
            val username= usernameEditText.editText?.text.toString()
            val password = passwordEditText.editText?.text.toString()

            viewModel.authenticate(username, password)
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

    private fun showErrorMessage() {
        resetErrorMessage()
        when(viewModel.authError) {
            LoginViewModel.AuthenticationError.UNKNOWN_USERNAME -> run {
                usernameEditText.error = getString(R.string.unknown_username)
                usernameEditText.requestFocus()
            }
            LoginViewModel.AuthenticationError.WRONG_PASSWORD -> run {
                passwordEditText.error = getString(R.string.wrong_password)
                passwordEditText.requestFocus()
        }
            LoginViewModel.AuthenticationError.INVALID_USERNAME -> run {
                usernameEditText.error = getString(R.string.invalid_username)
                usernameEditText.requestFocus()
            }
            LoginViewModel.AuthenticationError.INVALID_PASSWORD -> run {
                passwordEditText.error = getString(R.string.invalid_password)
                passwordEditText.requestFocus()
            }
            LoginViewModel.AuthenticationError.NO_ERROR -> {}
        }
    }

    private fun resetErrorMessage() {
        usernameEditText.error = null
        passwordEditText.error = null
    }



}
