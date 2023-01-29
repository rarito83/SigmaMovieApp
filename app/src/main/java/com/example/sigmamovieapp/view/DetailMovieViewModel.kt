package com.example.sigmamovieapp.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.sigmamovieapp.entity.Detail
import com.example.sigmamovieapp.repository.CinemaRepository

/**
 * Created by rarito
 */

class DetailMovieViewModel(private val repository: CinemaRepository) : ViewModel() {

    private lateinit var detail: LiveData<Detail>

    companion object {
        const val MOVIE_DETAIL = "movie detail"
    }

    fun setDetailMovie(id: String, select: String) {
        repository.apply {
            when (select) {
                MOVIE_DETAIL -> detail = loadMoviesDetails(id)
            }
        }
    }

    fun getDetailMovie() = detail
}