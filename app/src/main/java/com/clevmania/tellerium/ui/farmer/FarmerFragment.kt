package com.clevmania.tellerium.ui.farmer

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clevmania.tellerium.R

class FarmerFragment : Fragment() {

    companion object {
        fun newInstance() = FarmerFragment()
    }

    private lateinit var viewModel: FarmerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.farmer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FarmerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}