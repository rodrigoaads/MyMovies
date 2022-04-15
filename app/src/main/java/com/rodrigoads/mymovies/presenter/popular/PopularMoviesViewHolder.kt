package com.rodrigoads.mymovies.presenter.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rodrigoads.mymovies.BuildConfig
import com.rodrigoads.mymovies.R
import com.rodrigoads.mymovies.databinding.MovieItemLayoutBinding
import com.rodrigoads.mymovies.presenter.popular.model.PopularMoviesUiModel

class PopularMoviesViewHolder(
    popularMovieItemLayoutBinding: MovieItemLayoutBinding,
    private val onClickItem : (PopularMoviesUiModel) -> Unit
) : RecyclerView.ViewHolder(popularMovieItemLayoutBinding.root) {

    private val popularMoviePosterImage = popularMovieItemLayoutBinding.posterImageMovie

    fun bind(popularMoviesUiModel: PopularMoviesUiModel){
        Glide.with(itemView)
            .load(BuildConfig.GET_IMAGE_URL + popularMoviesUiModel.poster_path)
            .fallback(R.drawable.ic_baseline_broken_image_24)
            .into(popularMoviePosterImage)

        itemView.setOnClickListener {
            onClickItem(popularMoviesUiModel)
        }
    }

    companion object{
        fun create(parent: ViewGroup, onClickItem : (PopularMoviesUiModel) -> Unit) : PopularMoviesViewHolder{
            val inflater = LayoutInflater.from(parent.context)
            val item = MovieItemLayoutBinding.inflate(inflater, parent, false)
            return PopularMoviesViewHolder(item, onClickItem)
        }
    }
}