package com.rodrigoads.mymovies.presenter.popular

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class PopularMoviesLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PopularMoviesLoadStateViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PopularMoviesLoadStateViewHolder {
        return PopularMoviesLoadStateViewHolder.create(parent, retry)
    }

    override fun onBindViewHolder(holder: PopularMoviesLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}