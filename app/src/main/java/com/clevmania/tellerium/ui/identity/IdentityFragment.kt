package com.clevmania.tellerium.ui.identity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clevmania.tellerium.R
import com.clevmania.tellerium.ui.farmer.model.Farmer
import com.clevmania.tellerium.ui.farmerdetail.FarmerDetailViewModel
import com.clevmania.tellerium.utils.Constants
import com.clevmania.tellerium.utils.EventObserver
import com.clevmania.tellerium.utils.loadImage
import kotlinx.android.synthetic.main.fragment_identity.*


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
            farmerInfo.observe(viewLifecycleOwner, EventObserver{
                populateView(it)
            })
        }
    }

    companion object {
        fun newInstance(viewModel: FarmerDetailViewModel) =
            IdentityFragment().apply {this.viewModel = viewModel }
    }

    private fun populateView(farmer: Farmer){
        tvIdType.text = farmer.id_type
        tvIdNumber.text = farmer.id_no
        tvIssuedDate.text = farmer.issue_date
        tvExpiryDate.text = farmer.expiry_date
        tvIdImage.text = farmer.id_image
        ivIdImage.loadImage(getString(
            R.string.farmers_image,Constants.imageBaseUrl,farmer.id_image))
        tvFarmerPhoto.text = farmer.passport_photo
        ivFarmerPhoto.loadImage(getString(
            R.string.farmers_image,Constants.imageBaseUrl,farmer.passport_photo))
        farmer.fingerprint.splitToSequence(",").first().apply {
            tvFingerPrint.text = this
            ivFingerPrint.loadImage(getString(R.string.farmers_image, Constants.imageBaseUrl,this))
        }

    }
}