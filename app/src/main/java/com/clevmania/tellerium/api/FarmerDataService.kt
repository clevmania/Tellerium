package com.clevmania.tellerium.api

import com.clevmania.tellerium.ui.farmer.model.AllFarmersData
import com.clevmania.tellerium.ui.model.TelleriumApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author by Lawrence on 10/24/20.
 * for Tellerium
 */
interface FarmerDataService {
    @GET("v2/get-sample-farmers")
    suspend fun fetchFarmers(
        @Query("limit") limit: String
    ): TelleriumApiResponse<AllFarmersData>
}