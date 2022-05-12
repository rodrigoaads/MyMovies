package com.rodrigoads.mymovies.domain.model

import com.rodrigoads.mymovies.presenter.details.model.CrewUiModel

data class Crew(
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

fun Crew.toCrewUiModel() : CrewUiModel {
    return CrewUiModel(
        id = this.id,
        name = this.name,
        profile_path = this.profile_path,
        department = this.department,
        job = this.job
    )
}
