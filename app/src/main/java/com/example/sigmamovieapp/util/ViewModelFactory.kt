package com.example.sigmamovieapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sigmamovieapp.di.Injection
import com.example.sigmamovieapp.repository.CinemaRepository
import com.example.sigmamovieapp.view.DetailMovieViewModel
import com.example.sigmamovieapp.view.MovieViewModel

/**
 * Created by rarito
 */

class ViewModelFactory private constructor(private val repository: CinemaRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository()).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(repository) as T
            }

            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(repository) as T
            }

            else -> throw Throwable("Unknown ViewModel: " + modelClass.name)
        }
    }


}