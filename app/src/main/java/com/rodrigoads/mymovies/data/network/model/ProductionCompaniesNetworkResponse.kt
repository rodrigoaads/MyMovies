package com.rodrigoads.mymovies.data.network.model

data class ProductionCompaniesNetworkResponse(
    val name: String,
    val id: Int,
    val logo_path: String?,
    val origin_country: String
)
