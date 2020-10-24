package com.clevmania.tellerium.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.clevmania.tellerium.R
import com.clevmania.tellerium.ui.auth.AuthError
import com.clevmania.tellerium.ui.auth.AuthSuccess
import com.clevmania.tellerium.ui.auth.InvalidUser
import com.clevmania.tellerium.ui.base.BaseFragment
import com.clevmania.tellerium.utils.EventObserver
import com.clevmania.tellerium.utils.ValidationException
import com.clevmania.tellerium.utils.ValidationType
import com.clevmania.tellerium.utils.validate
import kotlinx.android.synthetic.main.register_fragment.*

class RegisterFragment : BaseFragment() {
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mbCreateAccount.setOnClickListener { registerUser() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel) {
            progress.observe(viewLifecycleOwner, EventObserver {
                toggleBlockingProgress(it)
            })

            authRequest.observe(viewLifecycleOwner, EventObserver {
                when (it) {
                    is AuthSuccess -> {
                        // navigate to farmer screen
                    }
                    is AuthError -> {
                        showErrorDialog(it.error)
                    }
                }
            })

        }
    }

    private fun registerUser() {
        try {
            val firstName =
                tilFirstName.validate(ValidationType.NAME, getString(R.string.first_name))
            val lastName = tilLastName.validate(ValidationType.NAME, getString(R.string.last_name))
            val email = tilEmail.validate(ValidationType.EMAIL, getString(R.string.email))
            val mobile = tilMobile.validate(ValidationType.PHONE, getString(R.string.mobile))
            val password =
                tilPassword.validate(ValidationType.PASSWORD, getString(R.string.password))

            // Remember to save user details in the db
            viewModel.registerUser(email, password)
        } catch (ex: ValidationException) {
            ex.printStackTrace()
        }
    }

}