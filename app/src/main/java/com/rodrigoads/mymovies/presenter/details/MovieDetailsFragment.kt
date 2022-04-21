package com.rodrigoads.mymovies.presenter.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.rodrigoads.mymovies.BuildConfig
import com.rodrigoads.mymovies.R
import com.rodrigoads.mymovies.databinding.FragmentMovieDetailsBinding
import com.rodrigoads.mymovies.presenter.MainActivity
import com.rodrigoads.mymovies.presenter.base.ResultUiState
import com.rodrigoads.mymovies.presenter.details.model.MovieDetailsUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private lateinit var movieDetailsBinding: FragmentMovieDetailsBinding
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieDetailsBinding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return movieDetailsBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieDetailsViewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            movieDetailsBinding.viewFlipperMovieDetails.displayedChild = when (it) {
                is ResultUiState.Loading -> {
                    FLIPPER_CHILD_MOVIE_DETAILS_LOADING_STATE
                }
                is ResultUiState.Success -> {
                    setMovieDetails(it.data)
                    FLIPPER_CHILD_MOVIE_DETAILS
                }
                is ResultUiState.Error -> {
                    movieDetailsBinding.includeViewPopularMoviesErrorState.buttonPopularMoviesTryAgain.visibility =
                        View.GONE
                    FLIPPER_CHILD_MOVIE_DETAILS_ERROR_STATE
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        if (movieDetailsViewModel.movieDetails.value == null){
            movieDetailsViewModel.getMovieDetails(args.id)
        }
    }

    private fun setMovieDetails(movieDetails: MovieDetailsUiModel) {
        movieDetailsBinding.includeViewMovieDetails.textViewMovieDetailsTitle.text =
            movieDetails.title
        movieDetailsBinding.includeViewMovieDetails.textViewMovieDetailsReleaseDate.text =
            movieDetails.formattedDate ?: "Desconhecido"
        movieDetailsBinding.includeViewMovieDetails.textViewMovieDetailsRuntime.text =
            movieDetails.formattedRuntime ?: "Desconhecido"
        movieDetailsBinding.includeViewMovieDetails.textViewMovieDetailsVoteAverage.text =
            movieDetails.vote_average.toString()
        movieDetailsBinding.includeViewMovieDetails.textViewMovieDetailsDescription.text =
            movieDetails.overview ?: "Desconhecido"

        Glide.with(this)
            .load(BuildConfig.GET_IMAGE_URL + movieDetails.poster_path)
            .fallback(R.drawable.ic_baseline_broken_image_24)
            .into(movieDetailsBinding.includeViewMovieDetails.imageViewMoviePoster)

        Glide.with(this)
            .load(BuildConfig.GET_IMAGE_URL + movieDetails.backdrop_path)
            .fallback(R.drawable.ic_baseline_broken_image_24)
            .into(movieDetailsBinding.includeViewMovieDetails.imageViewMovieBackdrop)
    }

    companion object {
        const val FLIPPER_CHILD_MOVIE_DETAILS_LOADING_STATE = 0
        const val FLIPPER_CHILD_MOVIE_DETAILS = 1
        const val FLIPPER_CHILD_MOVIE_DETAILS_ERROR_STATE = 2
    }
}