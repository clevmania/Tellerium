package com.clevmania.tellerium.ui.update.personal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clevmania.tellerium.data.FarmerLocalDataSource
import com.clevmania.tellerium.ui.farmer.model.Farmer
import com.clevmania.tellerium.utils.EventUtils
import com.clevmania.tellerium.utils.toErrorMessage
import kotlinx.coroutines.launch

class UpdatePersonalViewModel(private val farmerLocalDataSource: FarmerLocalDataSource) : ViewModel() {
    private val _progress = MutableLiveData<EventUtils<Boolean>>()
    val progress: LiveData<EventUtils<Boolean>> = _progress

    private val _error = MutableLiveData<EventUtils<String>>()
    val error: LiveData<EventUtils<String>> = _error

    private val _profileUpdate = MutableLiveData<EventUtils<Int>>()
    val profileUpdate : LiveData<EventUtils<Int>> = _profileUpdate

    fun updateDetails(farmerInfo: Farmer){
        viewModelScope.launch {
            _progress.value = EventUtils(true)
            try {
                val resp = farmerLocalDataSource.updateFarmer(farmerInfo)
                _profileUpdate.value = EventUtils(resp)
            } catch (ex: Exception) {
                ex.printStackTrace()
                _error.value = EventUtils(ex.toErrorMessage())
            } finally {
                _progress.value = EventUtils(false)
            }
        }
    }
}