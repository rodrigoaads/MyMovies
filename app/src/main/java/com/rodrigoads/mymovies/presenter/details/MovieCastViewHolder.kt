package com.rodrigoads.mymovies.presenter.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rodrigoads.mymovies.BuildConfig
import com.rodrigoads.mymovies.R
import com.rodrigoads.mymovies.databinding.MovieCastItemLayoutBinding
import com.rodrigoads.mymovies.presenter.details.model.CastUiModel

class MovieCastViewHolder(
    movieCastItemLayoutBinding: MovieCastItemLayoutBinding
) : RecyclerView.ViewHolder(movieCastItemLayoutBinding.root) {

    private val movieCastProfileImage = movieCastItemLayoutBinding.imageViewCast
    private val movieCastActorName = movieCastItemLayoutBinding.textActorName

    fun bind(castUiModel: CastUiModel){
        movieCastActorName.text = castUiModel.name
        Glide.with(itemView)
            .load(BuildConfig.GET_IMAGE_URL + castUiModel.profile_path)
            .fallback(R.drawable.ic_baseline_broken_image_24)
            .error(R.drawable.ic_baseline_person_24)
            .into(movieCastProfileImage)
    }

    companion object {
        fun create(parent: ViewGroup) : MovieCastViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val item = MovieCastItemLayoutBinding.inflate(inflater, parent, false)
            return MovieCastViewHolder(item)
        }
    }
}