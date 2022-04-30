package com.rodrigoads.mymovies.presenter.bycategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rodrigoads.mymovies.BuildConfig
import com.rodrigoads.mymovies.R
import com.rodrigoads.mymovies.databinding.MovieByCategoryModelBinding
import com.rodrigoads.mymovies.presenter.bycategory.model.MoviesByCategoryUiModel

class MoviesByCategoryViewHolder(
    movieByCategoryModelBinding: MovieByCategoryModelBinding,
    private val onClick: (MoviesByCategoryUiModel) -> Unit
) : RecyclerView.ViewHolder(movieByCategoryModelBinding.root) {

    private val imagePoster = movieByCategoryModelBinding.posterImageMovieByCategory

    fun bind(moviesByCategoryUiModel: MoviesByCategoryUiModel){
        Glide.with(itemView)
            .load(BuildConfig.GET_IMAGE_URL + moviesByCategoryUiModel.poster_path)
            .fallback(R.drawable.ic_baseline_broken_image_24)
            .error(R.drawable.ic_baseline_question_mark_24)
            .into(imagePoster)

        itemView.setOnClickListener {
            onClick(moviesByCategoryUiModel)
        }
    }

    companion object {
         fun create(parent: ViewGroup, onClick: (MoviesByCategoryUiModel) -> Unit) : MoviesByCategoryViewHolder{
            val inflater = LayoutInflater.from(parent.context)
            val item = MovieByCategoryModelBinding.inflate(inflater, parent, false)
            return MoviesByCategoryViewHolder(item, onClick)
        }
    }
}