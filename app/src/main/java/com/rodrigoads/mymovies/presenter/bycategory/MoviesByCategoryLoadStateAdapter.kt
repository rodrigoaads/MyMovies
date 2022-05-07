package com.rodrigoads.mymovies.presenter.bycategory

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class MoviesByCategoryLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<MoviesByCategoryLoadStateViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MoviesByCategoryLoadStateViewHolder {
        return MoviesByCategoryLoadStateViewHolder.create(parent, retry)
    }

    override fun onBindViewHolder(
        holder: MoviesByCategoryLoadStateViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }
}