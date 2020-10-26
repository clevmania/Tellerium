package com.clevmania.tellerium.ui.farmerdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clevmania.tellerium.data.FarmEntity
import com.clevmania.tellerium.data.FarmLocalDataSource
import com.clevmania.tellerium.data.FarmerLocalDataSource
import com.clevmania.tellerium.ui.farmer.model.Farmer
import com.clevmania.tellerium.utils.EventUtils
import com.clevmania.tellerium.utils.toErrorMessage
import kotlinx.coroutines.launch
import java.lang.Exception

class FarmerDetailViewModel(
    private val dataSource: FarmerLocalDataSource,
    private val farmDataSource: FarmLocalDataSource
) : ViewModel() {

    private val _progress = MutableLiveData<EventUtils<Boolean>>()
    val progress : LiveData<EventUtils<Boolean>> = _progress

    private val _error = MutableLiveData<EventUtils<String>>()
    val error : LiveData<EventUtils<String>> = _error

    private val _farmerInfo = MutableLiveData<EventUtils<Farmer>>()
    val farmerInfo : LiveData<EventUtils<Farmer>> = _farmerInfo

    private val _fID = MutableLiveData<EventUtils<String>>()
    val fID : LiveData<EventUtils<String>> = _fID

    private val _farmInfo = MutableLiveData<EventUtils<FarmEntity>>()
    val farmInfo : LiveData<EventUtils<FarmEntity>> = _farmInfo

    private val _sharedFarmerInfo = MutableLiveData<EventUtils<Farmer>>()
    val sharedFarmerInfo : LiveData<EventUtils<Farmer>> = _sharedFarmerInfo

    private val _sharedIdentityInfo = MutableLiveData<EventUtils<Farmer>>()
    val sharedIdentityInfo : LiveData<EventUtils<Farmer>> = _sharedIdentityInfo


    fun getFarmer(farmerId: String){
        viewModelScope.launch {
            try {
                val farmer = dataSource.getFarmerById(farmerId)
                _farmerInfo.value = EventUtils(farmer)

                val farm = farmDataSource.getCapturedFarmById(farmerId)
                _farmInfo.value = EventUtils(farm)

            }catch (ex : Exception){
                _error.value = EventUtils(ex.toErrorMessage())
            }
        }
    }

    fun shareFarmerDetail(farmer: Farmer){
        _sharedFarmerInfo.value = EventUtils(farmer)
        _sharedIdentityInfo.value = EventUtils(farmer)
        _fID.value = EventUtils(farmer.farmer_id)
    }
}