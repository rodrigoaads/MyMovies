package com.rodrigoads.mymovies.data.network.model

data class RequestNetworkResponse<T>(
    val page: Int,
    val results: ArrayList<T>,
    val total_results: Int,
    val total_pages: Int
)
