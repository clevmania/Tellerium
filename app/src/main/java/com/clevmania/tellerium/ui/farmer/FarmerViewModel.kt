package com.clevmania.tellerium.ui.farmer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clevmania.lerium.ui.farmer.model.AllFarmersData
import com.clevmania.tellerium.utils.Constants.FetchLimit
import com.clevmania.tellerium.utils.EventUtils
import com.clevmania.tellerium.utils.toErrorMessage
import kotlinx.coroutines.launch

class FarmerViewModel(private val repository: FarmerRepository) : ViewModel() {
    private val _progress = MutableLiveData<EventUtils<Boolean>>()
    val progress: LiveData<EventUtils<Boolean>> = _progress

    private val _error = MutableLiveData<EventUtils<String>>()
    val error: LiveData<EventUtils<String>> = _error

    private val _allFarmers = MutableLiveData<EventUtils<AllFarmersData>>()
    val allFarmers: LiveData<EventUtils<AllFarmersData>> = _allFarmers

    init { fetchFarmers() }

    private fun fetchFarmers() {
        viewModelScope.launch {
            _progress.value = EventUtils(true)
            try {
                val response = repository.fetchFarmersUpTo(FetchLimit)
                response.data?.let {
                    _allFarmers.value = EventUtils(it)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                _error.value = EventUtils(ex.toErrorMessage())
            } finally {
                _progress.value = EventUtils(false)
            }
        }
    }
}