package com.rodrigoads.mymovies.presenter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rodrigoads.mymovies.BuildConfig
import com.rodrigoads.mymovies.R
import com.rodrigoads.mymovies.databinding.MovieBySearchModelBinding
import com.rodrigoads.mymovies.presenter.search.model.SearchMovieUiModel

class SearchMovieViewHolder(
    movieBySearchModelBinding: MovieBySearchModelBinding,
    private val onClick: (SearchMovieUiModel) -> Unit
): RecyclerView.ViewHolder(movieBySearchModelBinding.root) {
    private val searchMoviePosterImage = movieBySearchModelBinding.imageViewPosterMoviesBySearch
    private val searchMovieTitle = movieBySearchModelBinding.textViewMovieBySearchMovieTitle
    private val searchMovieVoteCount = movieBySearchModelBinding.textViewMovieBySearchVoteAverage
    private val searchMovieReleaseDate = movieBySearchModelBinding.textViewMovieBySearchReleaseDate
    //private val searchMovieFavoriteIcon = movieBySearchModelBinding.imageViewMovieBySearchFavoriteMovie

    fun bind(searchMovieUiModel: SearchMovieUiModel){
        searchMovieTitle.text = searchMovieUiModel.title
        searchMovieVoteCount.text = searchMovieUiModel.vote_average.toString()
        searchMovieReleaseDate.text = searchMovieUiModel.formattedDate

        Glide.with(itemView)
            .load(BuildConfig.GET_IMAGE_URL + searchMovieUiModel.poster_path)
            .fallback(R.drawable.ic_baseline_broken_image_24)
            .error(R.drawable.ic_baseline_question_mark_24)
            .into(searchMoviePosterImage)

        itemView.setOnClickListener {
            onClick(searchMovieUiModel)
        }
    }

    companion object{
        fun create(parent: ViewGroup, onClick: (SearchMovieUiModel) -> Unit) : SearchMovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val item = MovieBySearchModelBinding.inflate(inflater, parent, false)
            return SearchMovieViewHolder(item, onClick)
        }
    }
}