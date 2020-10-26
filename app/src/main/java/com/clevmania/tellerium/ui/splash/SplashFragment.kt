package com.clevmania.tellerium.ui.splash

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clevmania.tellerium.R
import com.google.firebase.auth.FirebaseAuth

/**
 * @author by Lawrence on 10/24/20.
 * for Tellerium
 */
class SplashFragment : Fragment() {
    override fun onStart() {
        super.onStart()
        if(FirebaseAuth.getInstance().currentUser != null){
            findNavController().navigate(R.id.action_splashFragment_to_farmerFragment)
        }else{
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }
}