package com.rodrigoads.mymovies.presenter.watchlater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rodrigoads.mymovies.BuildConfig
import com.rodrigoads.mymovies.R
import com.rodrigoads.mymovies.databinding.MovieByWatchLaterLayoutBinding
import com.rodrigoads.mymovies.presenter.watchlater.model.WatchLaterUiModel

class WatchLaterViewHolder(
    movieByWatchLaterLayoutBinding: MovieByWatchLaterLayoutBinding,
    private val onClickItem: (Int) -> Unit,
    private val onClickRemoveItem: (Int) -> Unit
) : RecyclerView.ViewHolder(movieByWatchLaterLayoutBinding.root) {

    private val movieByWatchLaterPosterImage =
        movieByWatchLaterLayoutBinding.imageViewPosterMoviesByWatchLater
    private val movieByWatchLaterTitle =
        movieByWatchLaterLayoutBinding.textViewTitleMovieByWatchLater
    private val movieByWatchLaterRemoveIcon =
        movieByWatchLaterLayoutBinding.imageViewRemoveMovieByWatchLater

    fun bind(watchLaterUiModel: WatchLaterUiModel) {
        movieByWatchLaterTitle.text = watchLaterUiModel.title

        Glide.with(itemView)
            .load(BuildConfig.GET_IMAGE_URL + watchLaterUiModel.poster_path)
            .fallback(R.drawable.ic_baseline_broken_image_24)
            .error(R.drawable.ic_baseline_question_mark_24)
            .into(movieByWatchLaterPosterImage)

        itemView.setOnClickListener {
            onClickItem(watchLaterUiModel.id)
        }

        movieByWatchLaterRemoveIcon.setOnClickListener {
            onClickRemoveItem(watchLaterUiModel.id)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onClickItem: (Int) -> Unit,
            onClickRemoveItem: (Int) -> Unit
        ): WatchLaterViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val item = MovieByWatchLaterLayoutBinding.inflate(inflater, parent, false)
            return WatchLaterViewHolder(item, onClickItem, onClickRemoveItem)
        }
    }
}