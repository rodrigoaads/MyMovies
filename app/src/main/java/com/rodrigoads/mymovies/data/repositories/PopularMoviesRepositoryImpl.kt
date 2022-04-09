package com.rodrigoads.mymovies.data.repositories

import androidx.paging.PagingSource
import com.rodrigoads.mymovies.data.network.remote.PopularMoviesRemoteDataSource
import com.rodrigoads.mymovies.domain.model.PopularMovies
import com.rodrigoads.mymovies.framework.paging.PopularMoviesPagingSource
import javax.inject.Inject

class PopularMoviesRepositoryImpl @Inject constructor(
    private val popularMoviesRemoteDataSource: PopularMoviesRemoteDataSource
) : PopularMoviesRepository {
    override fun getRemotePopularMovies(firstItem : (PopularMovies?) -> Unit): PagingSource<Int, PopularMovies> {
        return PopularMoviesPagingSource(popularMoviesRemoteDataSource, firstItem)
    }
}

interface PopularMoviesRepository {
    fun getRemotePopularMovies(firstItem : (PopularMovies?) -> Unit): PagingSource<Int, PopularMovies>
}