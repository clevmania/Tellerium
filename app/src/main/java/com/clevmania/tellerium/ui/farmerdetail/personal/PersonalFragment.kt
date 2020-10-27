package com.clevmania.tellerium.ui.farmerdetail.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clevmania.tellerium.R
import com.clevmania.tellerium.ui.farmer.model.Farmer
import com.clevmania.tellerium.ui.farmerdetail.FarmerDetailFragmentDirections
import com.clevmania.tellerium.ui.farmerdetail.FarmerDetailViewModel
import com.clevmania.tellerium.utils.EventObserver
import kotlinx.android.synthetic.main.fragment_personal.*

/**
 * @author by Lawrence on 10/25/20.
 * for Tellerium
 */
class PersonalFragment : Fragment(){
    private lateinit var viewModel: FarmerDetailViewModel
    private lateinit var farmerInfo : Farmer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mbUpdateProfile.setOnClickListener {
            launchPersonalProfileUpdate()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel){
            sharedFarmerInfo.observe(viewLifecycleOwner, EventObserver{
                populateView(it)
            })
        }
    }

    companion object {
        fun newInstance(viewModel: FarmerDetailViewModel) =
            PersonalFragment().apply { this.viewModel = viewModel }
    }

    private fun populateView(farmer: Farmer){
        farmerInfo = farmer
        tvAddress.text = farmer.address
        tvRegNo.text = farmer.reg_no
        tvBvn.text = farmer.bvn
        tvFirstName.text = farmer.first_name
        tvMiddleName.text = farmer.middle_name
        tvSurName.text = farmer.surname
        tvDob.text = farmer.dob
        tvGender.text = farmer.gender
        tvNationality.text = farmer.nationality
        tvOccupation.text = farmer.occupation
        tvMaritalStatus.text = farmer.marital_status
        tvSpouseName.text = farmer.spouse_name
        tvCity.text = farmer.city
        tvLGA.text = farmer.lga
        tvMobile.text = farmer.mobile_no
        tvEmailAddress.text = farmer.email_address
    }

    private fun launchPersonalProfileUpdate(){
        val action = FarmerDetailFragmentDirections
            .actionFarmerDetailFragmentToUpdatePersonalFragment(farmerInfo)
        findNavController().navigate(action)
    }
}