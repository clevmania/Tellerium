package com.clevmania.tellerium.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clevmania.tellerium.data.FarmLocalDataSource
import com.clevmania.tellerium.data.FarmerLocalDataSource
import com.clevmania.tellerium.utils.EventUtils
import com.clevmania.tellerium.utils.toErrorMessage
import kotlinx.coroutines.launch
import java.lang.Exception

class DashBoardViewModel(
    private val farmLocalDataSource: FarmLocalDataSource,
    private val farmerLocalDataSource: FarmerLocalDataSource
) : ViewModel() {

    private val _progress = MutableLiveData<EventUtils<Boolean>>()
    val progress: LiveData<EventUtils<Boolean>> = _progress

    private val _error = MutableLiveData<EventUtils<String>>()
    val error: LiveData<EventUtils<String>> = _error

    private val _farmersCount = MutableLiveData<EventUtils<Int>>()
    val farmersCount : LiveData<EventUtils<Int>> = _farmersCount

    private val _farmCount = MutableLiveData<EventUtils<Int>>()
    val farmCount : LiveData<EventUtils<Int>> = _farmCount

    init {
        getTotalCount()
    }

    private fun getTotalCount(){
        viewModelScope.launch {
            _progress.value = EventUtils(true)

            try {
                val count = farmLocalDataSource.countFarm()
                _farmCount.value = EventUtils((count))

                val farmersCount = farmerLocalDataSource.countFarmers()
                _farmersCount.value = EventUtils((farmersCount))
            }catch (ex: Exception){
                _error.value = EventUtils(ex.toErrorMessage())
            }finally {
                _progress.value = EventUtils(false)
            }
        }
    }

}