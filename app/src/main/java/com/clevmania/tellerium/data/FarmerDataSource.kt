package com.clevmania.tellerium.data

import androidx.lifecycle.LiveData
import com.clevmania.tellerium.ui.farmer.model.Farmer

/**
 * @author by Lawrence on 10/25/20.
 * for Tellerium
 */
interface FarmerDataSource {
    fun getAllFarmers() : LiveData<List<Farmer>>

    suspend fun getFarmerById(farmerId : String): Farmer

    suspend fun insertAll(farmersList: List<Farmer>)

    suspend fun updateFarmer(farmer: Farmer) : Int

    suspend fun countFarmers(): Int

}