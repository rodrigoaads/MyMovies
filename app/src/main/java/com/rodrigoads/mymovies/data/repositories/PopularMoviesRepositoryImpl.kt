package com.rodrigoads.mymovies.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.rodrigoads.mymovies.data.network.base.ResultStatus
import com.rodrigoads.mymovies.data.network.remote.PopularMoviesDataSource
import com.rodrigoads.mymovies.domain.model.PopularMovies
import com.rodrigoads.mymovies.framework.paging.PopularMoviesPagingSource
import javax.inject.Inject

class PopularMoviesRepositoryImpl @Inject constructor(
    private val popularMoviesDataSource: PopularMoviesDataSource
) : PopularMoviesRepository {
    override fun getRemotePopularMovies(
        firstItem: (LiveData<ResultStatus<PopularMovies?>>) -> Unit
    ): PagingSource<Int, PopularMovies> {
        return PopularMoviesPagingSource(popularMoviesDataSource, firstItem)
    }
}

interface PopularMoviesRepository {
    fun getRemotePopularMovies(
        firstItem: (LiveData<ResultStatus<PopularMovies?>>) -> Unit
    ): PagingSource<Int, PopularMovies>
}