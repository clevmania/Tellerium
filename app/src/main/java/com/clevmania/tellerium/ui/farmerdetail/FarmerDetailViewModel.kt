package com.clevmania.tellerium.ui.farmerdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clevmania.tellerium.data.FarmerLocalDataSource
import com.clevmania.tellerium.ui.farmer.model.Farmer
import com.clevmania.tellerium.utils.EventUtils
import com.clevmania.tellerium.utils.toErrorMessage
import kotlinx.coroutines.launch
import java.lang.Exception

class FarmerDetailViewModel(private val dataSource: FarmerLocalDataSource) : ViewModel() {

    private val _progress = MutableLiveData<EventUtils<Boolean>>()
    val progress : LiveData<EventUtils<Boolean>> = _progress

    private val _error = MutableLiveData<EventUtils<String>>()
    val error : LiveData<EventUtils<String>> = _error

    private val _farmerInfo = MutableLiveData<EventUtils<Farmer>>()
    val farmerInfo : LiveData<EventUtils<Farmer>> = _farmerInfo


    fun getFarmer(farmerId: String){
        viewModelScope.launch {
            try {
                val farmer = dataSource.getFarmerById(farmerId)
                _farmerInfo.value = EventUtils(farmer)

            }catch (ex : Exception){
                _error.value = EventUtils(ex.toErrorMessage())
            }
        }
    }
}