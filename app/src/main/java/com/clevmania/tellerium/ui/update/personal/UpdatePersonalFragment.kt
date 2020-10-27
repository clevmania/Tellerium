package com.clevmania.tellerium.ui.update.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.clevmania.tellerium.R
import com.clevmania.tellerium.ui.base.BaseFragment
import com.clevmania.tellerium.ui.farmer.model.Farmer
import com.clevmania.tellerium.utils.EventObserver
import com.clevmania.tellerium.utils.InjectorUtils
import com.clevmania.tellerium.utils.ValidationType
import com.clevmania.tellerium.utils.validate
import kotlinx.android.synthetic.main.fragment_personal.*
import kotlinx.android.synthetic.main.update_personal_fragment.*

class UpdatePersonalFragment : BaseFragment() {
    private val args : UpdatePersonalFragmentArgs by navArgs()

    companion object {
        fun newInstance() = UpdatePersonalFragment()
    }

    private val viewModel by viewModels<UpdatePersonalViewModel> {
        InjectorUtils.provideUpdatePersonalViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.update_personal_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateView()
        mbUpdateProfile.setOnClickListener { updateProfile() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel){
            progress.observe(viewLifecycleOwner, EventObserver {
                toggleBlockingProgress(it)
            })

            error.observe(viewLifecycleOwner, EventObserver {
                showErrorDialog(it)
            })

            profileUpdate.observe(viewLifecycleOwner, EventObserver{
                findNavController().navigate(R.id.action_updatePersonalFragment_to_successFragment)
            })
        }
    }

    private fun populateView(){
        tieRegistrationNo.setText(args.farmerInfo.reg_no)
        tieFirstName.setText(args.farmerInfo.first_name)
        tieMiddleName.setText(args.farmerInfo.middle_name)
        tieSurname.setText(args.farmerInfo.middle_name)
        tieDob.setText(args.farmerInfo.dob)
        tieGender.setText(args.farmerInfo.gender)
        tieNationality.setText(args.farmerInfo.nationality)
        tieMaritalStatus.setText(args.farmerInfo.marital_status)
        tieOccupation.setText(args.farmerInfo.occupation)
        tieAddress.setText(args.farmerInfo.address)
        tieSpouseName.setText(args.farmerInfo.spouse_name)
        tieCity.setText(args.farmerInfo.city)
        tieLGA.setText(args.farmerInfo.lga)
        tieMobileNumber.setText(args.farmerInfo.mobile_no)
        tieEmail.setText(args.farmerInfo.email_address)
    }

    private fun updateProfile(){
        val regNo = tilRegistrationNo.validate(ValidationType.REQUIRED,getString(R.string.registration_number))
        val fName = tilFirstName.validate(ValidationType.NAME, getString(R.string.first_name))
        val mName = tilMiddleName.validate(ValidationType.NAME, getString(R.string.middle_name))
        val surname = tilSurname.validate(ValidationType.NAME, getString(R.string.surname))
        val dob = tilDob.validate(ValidationType.REQUIRED, getString(R.string.dob))
        val gender = tilGender.validate(ValidationType.REQUIRED, getString(R.string.gender))
        val nation = tilNationality.validate(ValidationType.REQUIRED, getString(R.string.nationality))
        val status = tilMaritalStatus.validate(ValidationType.REQUIRED, getString(R.string.marital_status))
        val occupation = tilOccupation.validate(ValidationType.REQUIRED, getString(R.string.occupation))
        val address = tilAddress.validate(ValidationType.REQUIRED, getString(R.string.address))
        val spouse = tilSpouseName.validate(ValidationType.REQUIRED, getString(R.string.spouse_name))
        val city = tilCity.validate(ValidationType.REQUIRED, getString(R.string.city))
        val lga = tilLGA.validate(ValidationType.REQUIRED, getString(R.string.lga))
        val mobile = tilMobileNumber.validate(ValidationType.PHONE, getString(R.string.mobile_number))
        val email = tilEmail.validate(ValidationType.EMAIL, getString(R.string.email))

        val updatedFarmerInfo = Farmer(address,args.farmerInfo.bvn,city,dob,email,
            args.farmerInfo.expiry_date,args.farmerInfo.farmer_id,args.farmerInfo.fingerprint,
            fName,gender,args.farmerInfo.id_image,args.farmerInfo.id_no,args.farmerInfo.id_type,
            args.farmerInfo.issue_date,lga,status,mName,mobile,nation,occupation,
            args.farmerInfo.passport_photo,regNo,spouse, args.farmerInfo.state,surname)

        viewModel.updateDetails(updatedFarmerInfo)
    }

}