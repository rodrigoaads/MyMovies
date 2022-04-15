package com.rodrigoads.mymovies.data.repositories

import androidx.paging.PagingSource
import com.rodrigoads.mymovies.data.network.remote.SimilarMoviesDataSource
import com.rodrigoads.mymovies.domain.model.MovieDetails
import com.rodrigoads.mymovies.domain.model.SimilarMovies
import com.rodrigoads.mymovies.framework.paging.SimilarMoviesPagingSource
import javax.inject.Inject

class SimilarMoviesRepositoryImpl @Inject constructor(
    private val similarMoviesDataSource: SimilarMoviesDataSource
) : SimilarMoviesRepository {
    override fun getRemoteSimilarMovies(id: Int): PagingSource<Int, SimilarMovies> {
        return SimilarMoviesPagingSource(similarMoviesDataSource, id)
    }
}

interface SimilarMoviesRepository {
    fun getRemoteSimilarMovies(id: Int) : PagingSource<Int, SimilarMovies>
}