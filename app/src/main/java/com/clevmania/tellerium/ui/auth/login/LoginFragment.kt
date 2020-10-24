package com.clevmania.tellerium.ui.auth.login

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
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : BaseFragment() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mbSignIn.setOnClickListener { signUpUser() }
        tvSignUp.setOnClickListener {
            // nnavigate to signnup
        }
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
                    is InvalidUser -> {
                        showErrorDialog(getString(R.string.wrong_credential))
                    }
                }
            })
        }
    }

    private fun signUpUser() {
        try {
            val email = tilEmail.validate(ValidationType.EMAIL, getString(R.string.email))
            val password = tilPassword.validate(
                ValidationType.PASSWORD, getString(R.string.password)
            )
            viewModel.signInWithEmail(email, password)

        } catch (ex: ValidationException) {
            ex.printStackTrace()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

}