package com.rodrigoads.mymovies.presenter.bycategory

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.rodrigoads.mymovies.presenter.bycategory.model.MoviesByCategoryUiModel

class MoviesByCategoryAdapter(
    private val onClick: (MoviesByCategoryUiModel) -> Unit
) : PagingDataAdapter<MoviesByCategoryUiModel, MoviesByCategoryViewHolder>(
    diffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesByCategoryViewHolder {
        return MoviesByCategoryViewHolder.create(parent, onClick)
    }

    override fun onBindViewHolder(holder: MoviesByCategoryViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<MoviesByCategoryUiModel>() {
            override fun areItemsTheSame(
                oldItem: MoviesByCategoryUiModel,
                newItem: MoviesByCategoryUiModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MoviesByCategoryUiModel,
                newItem: MoviesByCategoryUiModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}