package com.rodrigoads.mymovies.presenter.details

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class SimilarMoviesLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<SimilarMoviesLoadStateViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): SimilarMoviesLoadStateViewHolder {
        return SimilarMoviesLoadStateViewHolder.create(parent, retry)
    }

    override fun onBindViewHolder(holder: SimilarMoviesLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}