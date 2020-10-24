package com.clevmania.tellerium.ui.auth

/**
 * @author by Lawrence on 10/24/20.
 * for Tellerium
 */
sealed class AuthState()
object AuthSuccess : AuthState()
data class AuthError(val error: String) : AuthState()
object InvalidUser : AuthState()