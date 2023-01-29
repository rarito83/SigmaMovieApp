package com.example.sigmamovieapp.remote

import androidx.lifecycle.LiveData
import com.example.sigmamovieapp.entity.Detail
import com.example.sigmamovieapp.entity.Movie

/**
 * Created by rarito
 */

interface DataSource {

    fun loadMovies(): LiveData<List<Movie>>

    fun loadMoviesDetails(moviesId: String): LiveData<Detail>
}