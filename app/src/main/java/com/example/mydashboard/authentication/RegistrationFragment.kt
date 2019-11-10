package com.example.mydashboard.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mydashboard.R
import com.google.android.material.textfield.TextInputLayout
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class RegistrationFragment : Fragment() {

    // ViewModel
    @Inject
    lateinit var viewModel: RegistrationViewModel

    // Views
    private lateinit var usernameEditText: TextInputLayout
    private lateinit var passwordEditText: TextInputLayout
    private lateinit var emailEditText: TextInputLayout
    private lateinit var signOnButton: Button
    private lateinit var backToLoginScreenButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        // Init views
        usernameEditText = view.findViewById(R.id.registration_username_editlayout)
        passwordEditText = view.findViewById(R.id.registration_password_editlayout)
        emailEditText = view.findViewById(R.id.registration_email_editlayout)
        signOnButton = view.findViewById(R.id.registration_sign_on_button)
        backToLoginScreenButton = view.findViewById(R.id.registration_back_to_login)

        // Observe models
        viewModel.regisState.observe(this, Observer {regisState ->
            when (regisState) {
                RegistrationViewModel.RegistrationState.REGISTERED -> navController.popBackStack(R.id.homeFragment,false)
                RegistrationViewModel.RegistrationState.REGISTRATION_FAILED -> showErrorMessage()
                else -> {}
            }
        })

        // Init onClick listener
        signOnButton.setOnClickListener {
            val username = usernameEditText.editText?.text.toString()
            val password = passwordEditText.editText?.text.toString()
            val email = emailEditText.editText?.text.toString()
            viewModel.registerAccount(username, password, email)
        }

        backToLoginScreenButton.setOnClickListener {
            navController.popBackStack(R.id.loginFragment, false)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().findViewById<View>(R.id.toolbar)?.visibility = View.GONE
    }

    private fun showErrorMessage() {
        resetErrorMessage()
        when(viewModel.regisError) {
            RegistrationViewModel.RegistrationError.INVALID_USERNAME -> run {
                usernameEditText.error = getString(R.string.invalid_username)
                usernameEditText.requestFocus()
            }
            RegistrationViewModel.RegistrationError.ALREADY_USED_USERNAME -> run {
                usernameEditText.error = getString(R.string.already_existing_username)
                usernameEditText.requestFocus()
            }
            RegistrationViewModel.RegistrationError.INVALID_PASSWORD -> run {
                passwordEditText.error = getString(R.string.invalid_password)
                passwordEditText.requestFocus()
            }
            RegistrationViewModel.RegistrationError.INVALID_EMAIL -> run {
                emailEditText.error = getString(R.string.invalid_email)
                emailEditText.requestFocus()
            }
            RegistrationViewModel.RegistrationError.NO_ERROR -> {}
        }
    }

    private fun resetErrorMessage() {
        usernameEditText.error = null
        passwordEditText.error = null
        emailEditText.error = null
    }
}
