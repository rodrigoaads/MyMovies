package com.rodrigoads.mymovies.data.network.model.credits

import com.rodrigoads.mymovies.domain.model.Cast

data class CastNetworkResponse(
    val adult: Boolean?,
    val gender: Int?,
    val id: Int,
    val known_for_department: String?,
    val name: String?,
    val original_name: String?,
    val popularity: Double?,
    val profile_path: String?,
    val cast_id: Int?,
    val character: String?,
    val credit_id: String?,
    val order: Int?
)

fun CastNetworkResponse.toCast() : Cast {
    return Cast(
        adult = this.adult,
        gender = this.gender,
        id = this.id,
        known_for_department = this.known_for_department,
        name = this.name,
        original_name = this.original_name,
        popularity = this.popularity,
        profile_path = this.profile_path,
        cast_id = this.cast_id,
        character = this.character,
        credit_id = this.credit_id,
        order = this.order
    )
}
