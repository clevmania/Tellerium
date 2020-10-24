package com.clevmania.tellerium.ui.farmer

import com.clevmania.lerium.ui.farmer.model.AllFarmersData
import com.clevmania.tellerium.ui.api.FarmerDataService
import com.clevmania.tellerium.ui.model.TelleriumApiResponse

/**
 * @author by Lawrence on 10/24/20.
 * for Tellerium
 */
class FarmerRepository(private val apiService: FarmerDataService){
    suspend fun fetchFarmersUpTo(limit: String): TelleriumApiResponse<AllFarmersData> {
        return apiService.fetchFarmers(limit)
    }

}