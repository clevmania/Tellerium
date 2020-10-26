package com.clevmania.tellerium.ui.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.clevmania.tellerium.R
import com.clevmania.tellerium.data.FarmEntity
import com.clevmania.tellerium.ui.base.BaseFragment
import com.clevmania.tellerium.utils.*
import kotlinx.android.synthetic.main.add_farm_fragment.*

class AddFarmFragment : BaseFragment() {
    private val args: AddFarmFragmentArgs by navArgs()

    private val viewModel by viewModels<AddFarmViewModel> {
        InjectorUtils.provideAddFarmViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_farm_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mbSaveFarm.setOnClickListener { captureAndSaveFarmData() }
    }

    private fun captureAndSaveFarmData(){
        try {
            val farmName = tilFarmName.validate(ValidationType.NAME, "Farm Name")
            val location = tilFarmLocation.validate(ValidationType.NAME, "Farm Location")

            val lngOne = tilLongitude.validate(ValidationType.LONGITUDE,
                "First Longitude").toDouble()
            val latOne = tilLatitude.validate(ValidationType.LATITUDE,
                "First Latitude").toDouble()

            val lngTwo = tilLongitudeTwo.validate(ValidationType.LONGITUDE,
                "Second Longitude").toDouble()
            val latTwo = tilLatitudeTwo.validate(ValidationType.LATITUDE,
                "Second Latitude").toDouble()

            val lngThree = tilLongitudeThree.validate(ValidationType.LONGITUDE,
                "Third Longitude").toDouble()
            val latThree = tilLatitudeThree.validate(ValidationType.LATITUDE,
                "Third Latitude").toDouble()

            val lngFour = tilLongitudeFour.validate(ValidationType.LONGITUDE,
                "Fourth Longitude").toDouble()
            val latFour = tilLatitudeFour.validate(ValidationType.LATITUDE,
                "Fourth Latitude").toDouble()

            val farmRecord = FarmEntity(args.id,farmName,location,
                latOne,latTwo,latThree,latFour,
                lngOne,lngTwo,lngThree,lngFour
            )

            viewModel.saveCapturedFarm(farmRecord)

        }catch (ex: ValidationException){
            ex.printStackTrace()
        }
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

            newFarm.observe(viewLifecycleOwner, EventObserver{
                findNavController().navigate(R.id.action_addFarmFragment_to_successFragment)
            })
        }
    }

}