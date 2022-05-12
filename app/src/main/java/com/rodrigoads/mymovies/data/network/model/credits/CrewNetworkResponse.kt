package com.rodrigoads.mymovies.data.network.model.credits

import com.rodrigoads.mymovies.domain.model.Crew

data class CrewNetworkResponse(
    val adult: Boolean?,
    val gender: Int?,
    val id: Int,
    val known_for_department: String?,
    val name: String?,
    val original_name: String?,
    val popularity: Double?,
    val profile_path: String?,
    val credit_id: String?,
    val department: String?,
    val job: String?
)

fun CrewNetworkResponse.toCrew() : Crew {
    return Crew(
        adult = this.adult,
        gender = this.gender,
        id = this.id,
        known_for_department = this.known_for_department,
        name = this.name,
        original_name = this.original_name,
        popularity = this.popularity,
        profile_path = this.profile_path,
        credit_id = this.credit_id,
        department = this.department,
        job = this.job
    )
}
