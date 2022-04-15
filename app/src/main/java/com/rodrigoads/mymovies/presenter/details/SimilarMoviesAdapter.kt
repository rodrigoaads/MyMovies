package com.rodrigoads.mymovies.presenter.details

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.rodrigoads.mymovies.presenter.details.model.SimilarMoviesUiModel

class SimilarMoviesAdapter(
    private val onClickItem : (SimilarMoviesUiModel) -> Unit
) : PagingDataAdapter<SimilarMoviesUiModel, SimilarMoviesViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMoviesViewHolder {
        return SimilarMoviesViewHolder.create(parent, onClickItem)
    }

    override fun onBindViewHolder(holder: SimilarMoviesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object{
        val diffCallback = object : DiffUtil.ItemCallback<SimilarMoviesUiModel>(){
            override fun areItemsTheSame(
                oldItem: SimilarMoviesUiModel,
                newItem: SimilarMoviesUiModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: SimilarMoviesUiModel,
                newItem: SimilarMoviesUiModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}