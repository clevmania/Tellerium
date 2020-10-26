package com.clevmania.tellerium.data

import androidx.lifecycle.LiveData
import com.clevmania.tellerium.ui.farmer.model.Farmer

/**
 * @author by Lawrence on 10/25/20.
 * for Tellerium
 */
class FarmerLocalDataSource(private val farmerDao: FarmerDao): FarmerDataSource {
    override fun getAllFarmers(): LiveData<List<Farmer>> {
        return farmerDao.getAllFarmers()
    }

    override suspend fun getFarmerById(farmerId: String): Farmer {
        return farmerDao.getFarmerById(farmerId)
    }

    override suspend fun insertAll(farmersList: List<Farmer>) {
        return farmerDao.insertAll(farmersList)
    }

    override suspend fun updateFarmer(farmer: Farmer): Int {
        return farmerDao.updateFarmer(farmer)
    }

    override suspend fun countFarmers(): Int {
        return farmerDao.countFarmers()
    }
}