package com.clevmania.lerium.ui.farmer.model

data class AllFarmersData(
    val farmers: List<Farmer>,
    val imageBaseUrl: String,
    val totalRec: Int
)