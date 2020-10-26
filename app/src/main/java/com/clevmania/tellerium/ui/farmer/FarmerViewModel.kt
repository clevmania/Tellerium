package com.clevmania.tellerium.ui.farmer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clevmania.tellerium.ui.farmer.model.Farmer
import com.clevmania.tellerium.utils.Constants.FetchLimit
import com.clevmania.tellerium.utils.EventUtils
import com.clevmania.tellerium.utils.toErrorMessage
import kotlinx.coroutines.launch

class FarmerViewModel(private val repository: FarmerRepository) : ViewModel() {
    private val _progress = MutableLiveData<EventUtils<Boolean>>()
    val progress: LiveData<EventUtils<Boolean>> = _progress

    private val _error = MutableLiveData<EventUtils<String>>()
    val error: LiveData<EventUtils<String>> = _error

    var allFarmers: LiveData<List<Farmer>> = repository.getFarmers()

    init {
        if(allFarmers.value.isNullOrEmpty()){
            fetchFarmers()
        }
    }

    private fun fetchFarmers() {
        viewModelScope.launch {
            _progress.value = EventUtils(true)
            try {
                repository.fetchFarmersUpTo(FetchLimit, isFetchNeeded = true)

            } catch (ex: Exception) {
                ex.printStackTrace()
                _error.value = EventUtils(ex.toErrorMessage())
            } finally {
                _progress.value = EventUtils(false)
            }
        }
    }
}