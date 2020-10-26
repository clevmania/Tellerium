package com.clevmania.tellerium.ui.farmer

import androidx.lifecycle.LiveData
import com.clevmania.tellerium.api.FarmerDataService
import com.clevmania.tellerium.data.FarmerLocalDataSource
import com.clevmania.tellerium.ui.farmer.model.Farmer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author by Lawrence on 10/24/20.
 * for Tellerium
 */
class FarmerRepository(
    private val apiService: FarmerDataService,
    private val dataSource: FarmerLocalDataSource
) {

    fun getFarmers(): LiveData<List<Farmer>> {
        return dataSource.getAllFarmers()
    }

    suspend fun fetchFarmersUpTo(limit: String, isFetchNeeded: Boolean = false) {
        if (isFetchNeeded){
            withContext(Dispatchers.IO) {
                val allFarmersData = apiService.fetchFarmers(limit)
                allFarmersData.data?.let {
                    dataSource.insertAll(it.farmers)
                }
            }
        }
    }

}