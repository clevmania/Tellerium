package com.clevmania.tellerium.ui.farmer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Farmer(
    val address: String,
    val bvn: String,
    val city: String,
    val dob: String,
    val email_address: String,
    val expiry_date: String,
    @PrimaryKey()
    val farmer_id: String,
    val fingerprint: String,
    val first_name: String,
    val gender: String,
    val id_image: String,
    val id_no: String,
    val id_type: String,
    val issue_date: String,
    val lga: String,
    val marital_status: String,
    val middle_name: String,
    val mobile_no: String,
    val nationality: String,
    val occupation: String,
    val passport_photo: String,
    val reg_no: String,
    val spouse_name: String,
    val state: String,
    val surname: String
)