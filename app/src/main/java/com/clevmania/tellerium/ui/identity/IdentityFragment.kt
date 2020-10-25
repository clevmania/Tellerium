package com.clevmania.tellerium.ui.identity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clevmania.tellerium.R
import com.clevmania.tellerium.ui.farmerdetail.FarmerDetailViewModel


class IdentityFragment : Fragment() {
    private lateinit var viewModel: FarmerDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_identity, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel){

        }
    }

    companion object {
        fun newInstance(viewModel: FarmerDetailViewModel) =
            IdentityFragment().apply {this.viewModel = viewModel }
    }
}