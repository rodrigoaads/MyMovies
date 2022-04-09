package com.rodrigoads.mymovies.presenter.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.rodrigoads.mymovies.R
import com.rodrigoads.mymovies.databinding.FragmentPopularMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {
    private lateinit var popularMoviesBinding : FragmentPopularMoviesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        popularMoviesBinding = FragmentPopularMoviesBinding.inflate(layoutInflater, container, false)
        return popularMoviesBinding.root
    }
}