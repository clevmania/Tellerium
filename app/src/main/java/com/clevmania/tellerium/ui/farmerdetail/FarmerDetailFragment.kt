package com.clevmania.tellerium.ui.farmerdetail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clevmania.tellerium.R

class FarmerDetailFragment : Fragment() {

    companion object {
        fun newInstance() = FarmerDetailFragment()
    }

    private lateinit var viewModel: FarmerDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.farmer_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FarmerDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}