package com.rodrigoads.mymovies.presenter.details

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rodrigoads.mymovies.presenter.details.model.CastUiModel

class MovieCastAdapter : ListAdapter<CastUiModel, MovieCastViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCastViewHolder {
        return MovieCastViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MovieCastViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<CastUiModel>(){
            override fun areItemsTheSame(oldItem: CastUiModel, newItem: CastUiModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CastUiModel, newItem: CastUiModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}