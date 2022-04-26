package com.rodrigoads.mymovies.data.repositories

import androidx.paging.PagingSource
import com.rodrigoads.mymovies.data.network.remote.MoviesByCategoryDataSource
import com.rodrigoads.mymovies.domain.model.MoviesByCategory
import com.rodrigoads.mymovies.framework.paging.MoviesByCategoryPagingSource
import javax.inject.Inject

class MoviesByCategoryRepositoryImpl @Inject constructor(
    private val moviesByCategoryDataSource: MoviesByCategoryDataSource
): MoviesByCategoryRepository {
    override fun getRemoteMoviesByCategory(genre: Int): PagingSource<Int, MoviesByCategory> {
        return MoviesByCategoryPagingSource(moviesByCategoryDataSource, genre)
    }
}

interface MoviesByCategoryRepository {
    fun getRemoteMoviesByCategory(genre: Int) : PagingSource<Int, MoviesByCategory>
}