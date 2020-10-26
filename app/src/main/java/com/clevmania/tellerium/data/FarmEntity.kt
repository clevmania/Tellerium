package com.clevmania.tellerium.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author by Lawrence on 10/26/20.
 * for Tellerium
 */
@Entity(tableName = "farm")
data class FarmEntity(
    @PrimaryKey()
    val farmer_id: String,
    val farm_name: String,
    val farm_location: String,
    val lat_one: Double,
    val lat_two: Double,
    val lat_three: Double,
    val lat_four: Double,
    val lng_one: Double,
    val lng_two: Double,
    val lng_three: Double,
    val lng_four: Double
)