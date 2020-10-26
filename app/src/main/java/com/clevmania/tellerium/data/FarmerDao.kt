package com.clevmania.tellerium.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.clevmania.tellerium.ui.farmer.model.Farmer

/**
 * @author by Lawrence on 10/25/20.
 * for Tellerium
 */
@Dao
interface FarmerDao{
    @Query("Select * FROM farmer")
    fun getAllFarmers() : LiveData<List<Farmer>>

    @Query("Select COUNT(*) FROM farmer")
    suspend fun countFarmers(): Int

    @Query("SELECT * FROM farmer where farmer_id=:farmerId")
    suspend fun getFarmerById(farmerId : String): Farmer

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(farmers: List<Farmer>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFarmer(farmer: Farmer) : Int
}