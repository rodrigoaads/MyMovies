package com.rodrigoads.mymovies.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.rodrigoads.mymovies.data.network.base.ResultStatus
import com.rodrigoads.mymovies.data.network.remote.PopularMoviesRemoteDataSource
import com.rodrigoads.mymovies.domain.model.PopularMovies
import com.rodrigoads.mymovies.framework.paging.PopularMoviesPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularMoviesRepositoryImpl @Inject constructor(
    private val popularMoviesRemoteDataSource: PopularMoviesRemoteDataSource
) : PopularMoviesRepository {
    override fun getRemotePopularMovies(
        firstItem: (LiveData<ResultStatus<PopularMovies?>>) -> Unit
    ): PagingSource<Int, PopularMovies> {
        return PopularMoviesPagingSource(popularMoviesRemoteDataSource, firstItem)
    }
}

interface PopularMoviesRepository {
    fun getRemotePopularMovies(
        firstItem: (LiveData<ResultStatus<PopularMovies?>>) -> Unit
    ): PagingSource<Int, PopularMovies>
}