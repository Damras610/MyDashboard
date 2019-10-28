package com.example.mydashboard.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mydashboard.R


class RegistrationFragment : Fragment() {

    // View models
    private lateinit var registrationViewModel: RegistrationViewModel

    // Views
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var signOnButton: Button
    private lateinit var backToLoginScreenButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        // Init views
        usernameEditText = view.findViewById(R.id.registration_username_edit)
        passwordEditText = view.findViewById(R.id.registration_password_edit)
        emailEditText = view.findViewById(R.id.registration_email_edit)
        signOnButton = view.findViewById(R.id.registration_sign_on_button)
        backToLoginScreenButton = view.findViewById(R.id.registration_back_to_login)

        // Init onClick listener
        signOnButton.setOnClickListener {

        }

        backToLoginScreenButton.setOnClickListener {
            navController.popBackStack(R.id.loginFragment, false)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().findViewById<Toolbar>(R.id.toolbar)?.visibility = View.GONE
    }

}
