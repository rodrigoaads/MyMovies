package com.rodrigoads.mymovies.presenter.search

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class SearchMoviesLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<SearchMoviesLoadStateViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): SearchMoviesLoadStateViewHolder {
        return SearchMoviesLoadStateViewHolder.create(parent, retry)
    }

    override fun onBindViewHolder(holder: SearchMoviesLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}