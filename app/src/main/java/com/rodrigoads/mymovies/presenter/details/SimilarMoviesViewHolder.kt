package com.rodrigoads.mymovies.presenter.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rodrigoads.mymovies.BuildConfig
import com.rodrigoads.mymovies.R
import com.rodrigoads.mymovies.databinding.MovieItemLayoutBinding
import com.rodrigoads.mymovies.presenter.details.model.SimilarMoviesUiModel

class SimilarMoviesViewHolder(
    similarMovieItemLayoutBinding: MovieItemLayoutBinding,
    private val onClickItem : (SimilarMoviesUiModel) -> Unit
) : RecyclerView.ViewHolder(similarMovieItemLayoutBinding.root) {

    private val similarMoviePosterImage = similarMovieItemLayoutBinding.posterImageMovie

    fun bind(similarMoviesUiModel: SimilarMoviesUiModel){
        Glide.with(itemView)
            .load(BuildConfig.GET_IMAGE_URL + similarMoviesUiModel.poster_path)
            .fallback(R.drawable.ic_baseline_broken_image_24)
            .error(R.drawable.ic_baseline_question_mark_24)
            .into(similarMoviePosterImage)

        itemView.setOnClickListener {
            onClickItem(similarMoviesUiModel)
        }
    }

    companion object{
        fun create(parent: ViewGroup, onClickItem : (SimilarMoviesUiModel) -> Unit) : SimilarMoviesViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val item = MovieItemLayoutBinding.inflate(inflater, parent, false)
            return SimilarMoviesViewHolder(item, onClickItem)
        }
    }
}