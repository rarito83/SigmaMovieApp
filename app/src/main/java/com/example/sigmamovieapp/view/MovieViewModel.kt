package com.example.sigmamovieapp.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.sigmamovieapp.entity.Movie
import com.example.sigmamovieapp.repository.CinemaRepository

/**
 * Created by rarito
 */

class MovieViewModel(private val repository: CinemaRepository) : ViewModel() {

    fun getMovies(): LiveData<List<Movie>> = repository.loadMovies()

}