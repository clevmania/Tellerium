package com.clevmania.tellerium.ui.splash

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clevmania.tellerium.R

/**
 * @author by Lawrence on 10/24/20.
 * for Tellerium
 */
class SplashFragment : Fragment() {
    override fun onStart() {
        super.onStart()
        findNavController().navigate(R.id.action_splashFragment_to_farmerFragment)
    }
}