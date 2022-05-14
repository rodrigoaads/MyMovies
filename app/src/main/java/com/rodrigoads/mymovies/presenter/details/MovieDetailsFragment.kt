package com.rodrigoads.mymovies.presenter.details

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.rodrigoads.mymovies.BuildConfig
import com.rodrigoads.mymovies.R
import com.rodrigoads.mymovies.databinding.FragmentMovieDetailsBinding
import com.rodrigoads.mymovies.presenter.MainActivity
import com.rodrigoads.mymovies.presenter.base.ResultUiState
import com.rodrigoads.mymovies.presenter.details.model.MovieDetailsUiModel
import com.rodrigoads.mymovies.presenter.watchlater.WatchLaterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private lateinit var movieDetailsBinding: FragmentMovieDetailsBinding
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
    private val watchLaterViewModel: WatchLaterViewModel by activityViewModels()

    private lateinit var icAddWatchList: Drawable
    private lateinit var icRemoveWatchList: Drawable

    private lateinit var movieDetails: MovieDetailsUiModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieDetailsBinding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return movieDetailsBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        icAddWatchList =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_add_24)!!
        icRemoveWatchList =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_highlight_off_24)!!

        movieDetailsViewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            movieDetailsBinding.viewFlipperMovieDetails.displayedChild = when (it) {
                is ResultUiState.Loading -> {
                    FLIPPER_CHILD_MOVIE_DETAILS_LOADING_STATE
                }
                is ResultUiState.Success -> {
                    movieDetails = it.data
                    setMovieDetails(movieDetails)
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
        if (movieDetailsViewModel.movieDetails.value == null) {
            movieDetailsViewModel.getMovieDetails(args.id)
        }
    }

    override fun onResume() {
        super.onResume()
        val checkWatchLaterStatus = watchLaterViewModel.checkWatchLaterMovieStatus(args.id)
        if (movieDetailsViewModel.movieDetails.value is ResultUiState.Success) {
            if (movieDetails.watchLater) {
                if (!checkWatchLaterStatus) {
                    movieDetailsBinding.includeViewMovieDetails.textViewMovieDetailsAddList
                        .setCompoundDrawablesWithIntrinsicBounds(
                            null,
                            icAddWatchList,
                            null,
                            null
                        )
                    this.movieDetails.watchLater = false
                }
            }
        }
    }

    private fun setMovieDetails(movieDetails: MovieDetailsUiModel) {

        getWatchLaterStatus(movieDetails.watchLater)

        movieDetailsBinding.includeViewMovieDetails.textViewMovieDetailsTitle.text =
            movieDetails.title
        movieDetailsBinding.includeViewMovieDetails.textViewMovieDetailsReleaseDate.text =
            movieDetails.formattedDate ?: getString(R.string.unknown)
        movieDetailsBinding.includeViewMovieDetails.textViewMovieDetailsRuntime.text =
            movieDetails.formattedRuntime ?: getString(R.string.unknown)
        movieDetailsBinding.includeViewMovieDetails.textViewMovieDetailsVoteAverage.text =
            movieDetails.vote_average.toString()
        movieDetailsBinding.includeViewMovieDetails.textViewMovieDetailsDescription.text =
            movieDetails.overview ?: getString(R.string.unknown)

        Glide.with(this)
            .load(BuildConfig.GET_IMAGE_URL + movieDetails.poster_path)
            .fallback(R.drawable.ic_baseline_broken_image_24)
            .error(R.drawable.ic_baseline_question_mark_24)
            .into(movieDetailsBinding.includeViewMovieDetails.imageViewMoviePoster)

        Glide.with(this)
            .load(BuildConfig.GET_IMAGE_URL + movieDetails.backdrop_path)
            .fallback(R.drawable.ic_baseline_broken_image_24)
            .error(R.drawable.ic_baseline_image_not_supported_24)
            .into(movieDetailsBinding.includeViewMovieDetails.imageViewMovieBackdrop)

        movieDetailsBinding.includeViewMovieDetails.textViewMovieDetailsAddList.setOnClickListener {
            lifecycleScope.launch {
                getWatchLaterStatus(
                    watchLaterViewModel.managementWatchLaterMovie(movieDetails).also {
                        movieDetails.watchLater = it
                    })
            }
        }
    }

    private fun getWatchLaterStatus(status: Boolean) {
        if (status) {
            movieDetailsBinding.includeViewMovieDetails.textViewMovieDetailsAddList
                .setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    icRemoveWatchList,
                    null,
                    null
                )
        } else {
            movieDetailsBinding.includeViewMovieDetails.textViewMovieDetailsAddList
                .setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    icAddWatchList,
                    null,
                    null
                )
        }
    }

    companion object {
        const val FLIPPER_CHILD_MOVIE_DETAILS_LOADING_STATE = 0
        const val FLIPPER_CHILD_MOVIE_DETAILS = 1
        const val FLIPPER_CHILD_MOVIE_DETAILS_ERROR_STATE = 2
    }
}