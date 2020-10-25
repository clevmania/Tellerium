package com.clevmania.tellerium.ui.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clevmania.tellerium.R
import com.clevmania.tellerium.ui.farmerdetail.FarmerDetailViewModel

/**
 * @author by Lawrence on 10/25/20.
 * for Tellerium
 */
class PersonalFragment : Fragment(){
    private lateinit var viewModel: FarmerDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel){

        }
    }

    companion object {
        fun newInstance(viewModel: FarmerDetailViewModel) =
            PersonalFragment().apply { this.viewModel = viewModel }
    }
}