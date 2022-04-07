package com.rodrigoads.mymovies.presenter.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigoads.mymovies.databinding.FragmentSearchMovieBinding

class SearchMovieFragment : Fragment() {
    private lateinit var searchMovieBinding: FragmentSearchMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchMovieBinding = FragmentSearchMovieBinding.inflate(layoutInflater, container, false)
        return searchMovieBinding.root
    }
}