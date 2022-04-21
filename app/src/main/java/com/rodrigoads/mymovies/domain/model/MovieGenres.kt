package com.rodrigoads.mymovies.domain.model

import com.rodrigoads.mymovies.presenter.categories.model.CategoriesUiModel

data class MovieGenres(
    val id: Int,
    val name: String
)

fun MovieGenres.toCategoriesUiModel() : CategoriesUiModel {
    return CategoriesUiModel(
        id = this.id,
        name = this.name
    )
}
