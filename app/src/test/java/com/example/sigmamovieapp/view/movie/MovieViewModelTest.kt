package com.example.sigmamovieapp.view.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.sigmamovieapp.entity.Movie
import com.example.sigmamovieapp.repository.CinemaRepository
import com.example.sigmamovieapp.util.DataDummy
import com.example.sigmamovieapp.view.MovieViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: CinemaRepository

    @Mock
    private lateinit var observer: Observer<List<Movie>>

    @Before
    fun setup() {
        movieViewModel = MovieViewModel(repository)
    }

    @Test
    fun getMovies() {
        val movieDummy = DataDummy.getMovies()
        val movies = MutableLiveData<List<Movie>>()
        movies.value = movieDummy

        `when`(repository.loadMovies()).thenReturn(movies)
        val movie = movieViewModel.getMovies().value
        verify(repository).loadMovies()
        Assert.assertNotNull(movie)
        Assert.assertEquals(3, movie?.size)

        movieViewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(movieDummy)
    }
}