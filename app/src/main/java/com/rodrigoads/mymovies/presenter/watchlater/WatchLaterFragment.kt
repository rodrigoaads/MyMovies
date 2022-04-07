package com.rodrigoads.mymovies.presenter.watchlater

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigoads.mymovies.R
import com.rodrigoads.mymovies.databinding.FragmentWatchLaterBinding

class WatchLaterFragment : Fragment() {
    private lateinit var watchLaterBinding: FragmentWatchLaterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        watchLaterBinding = FragmentWatchLaterBinding.inflate(layoutInflater, container, false)
        return watchLaterBinding.root
    }
}