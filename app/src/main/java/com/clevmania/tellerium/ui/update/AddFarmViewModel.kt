package com.clevmania.tellerium.ui.update

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clevmania.tellerium.data.FarmEntity
import com.clevmania.tellerium.data.FarmLocalDataSource
import com.clevmania.tellerium.utils.EventUtils
import com.clevmania.tellerium.utils.toErrorMessage
import kotlinx.coroutines.launch
import java.lang.Exception

class AddFarmViewModel(private val farmLocalDataSource: FarmLocalDataSource) : ViewModel() {
    private val _progress = MutableLiveData<EventUtils<Boolean>>()
    val progress : LiveData<EventUtils<Boolean>> = _progress

    private val _error = MutableLiveData<EventUtils<String>>()
    val error : LiveData<EventUtils<String>> = _error

    private val _newFarm = MutableLiveData<EventUtils<Int>>()
    val newFarm : LiveData<EventUtils<Int>> = _newFarm

    fun saveCapturedFarm(farm: FarmEntity){
        viewModelScope.launch {
            _progress.value = EventUtils(true)
            try {
                val response = farmLocalDataSource.captureFarm(farm)
                _newFarm.value = EventUtils(response)
            }catch (ex : Exception){
                _error.value = EventUtils(ex.toErrorMessage())
            }finally {
                _progress.value = EventUtils(false)
            }
        }

    }
}