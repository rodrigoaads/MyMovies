package com.rodrigoads.mymovies.data.repositories

import androidx.paging.PagingSource
import com.rodrigoads.mymovies.data.network.remote.MoviesBySearchDataSource
import com.rodrigoads.mymovies.domain.model.MoviesBySearch
import com.rodrigoads.mymovies.framework.paging.MoviesBySearchPagingSource
import javax.inject.Inject

class MoviesBySearchRepositoryImpl @Inject constructor(
    private val moviesBySearchDataSource: MoviesBySearchDataSource
) : MoviesBySearchRepository {
    override fun getMoviesBySearch(query: String): PagingSource<Int, MoviesBySearch> {
        return MoviesBySearchPagingSource(moviesBySearchDataSource, query)
    }
}

interface MoviesBySearchRepository {
    fun getMoviesBySearch(query: String): PagingSource<Int, MoviesBySearch>
}