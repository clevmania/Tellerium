package com.clevmania.tellerium.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clevmania.tellerium.ui.auth.AuthError
import com.clevmania.tellerium.ui.auth.AuthState
import com.clevmania.tellerium.ui.auth.AuthSuccess
import com.clevmania.tellerium.ui.auth.InvalidUser
import com.clevmania.tellerium.utils.EventUtils
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {
    private val _progress = MutableLiveData<EventUtils<Boolean>>()
    val progress: LiveData<EventUtils<Boolean>> = _progress

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authRequest = MutableLiveData<EventUtils<AuthState>>()
    val authRequest: LiveData<EventUtils<AuthState>> = _authRequest

    fun signInWithEmail(email: String, password: String) {
        _progress.value = EventUtils(true)

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _progress.value = EventUtils(false)
                if (task.isSuccessful) {
                    _authRequest.value = EventUtils(AuthSuccess)
                }
            }.addOnFailureListener {
                _progress.value = EventUtils(false)
                it.localizedMessage?.let { error ->
                    _authRequest.value = EventUtils(
                        AuthError(
                            error
                        )
                    )
                }
            }
    }

}