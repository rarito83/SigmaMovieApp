package com.example.sigmamovieapp.view.movie.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.sigmamovieapp.entity.Detail
import com.example.sigmamovieapp.repository.CinemaRepository
import com.example.sigmamovieapp.util.DataDummy
import com.example.sigmamovieapp.view.DetailMovieViewModel
import com.example.sigmamovieapp.view.DetailMovieViewModel.Companion.MOVIE_DETAIL
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel

    private val movieDummy = DataDummy.getMovieDetail()
    private val movieDummyId = movieDummy.id.toString()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: CinemaRepository

    @Mock
    private lateinit var movieObserver: Observer<Detail>

    @Before
    fun setUpMovie() {
        viewModel = DetailMovieViewModel(repository)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<Detail>()
        movie.value = movieDummy

        `when`(repository.loadMoviesDetails(movieDummyId)).thenReturn(movie)
        viewModel.setDetailMovie(movieDummyId, MOVIE_DETAIL)
        val detailEntity = viewModel.getDetailMovie().value as Detail
        verify(repository).loadMoviesDetails(movieDummyId)

        Assert.assertNotNull(detailEntity)
        assertEquals(movieDummy.backdropPath, detailEntity.backdropPath)
        assertEquals(movieDummy.genres, detailEntity.genres)
        assertEquals(movieDummy.id, detailEntity.id)
        assertEquals(movieDummy.originalTitle, detailEntity.originalTitle)
        assertEquals(movieDummy.overview, detailEntity.overview)
        assertEquals(movieDummy.posterPath, detailEntity.posterPath)
        assertEquals(movieDummy.releaseDate, detailEntity.releaseDate)
        assertEquals(movieDummy.voteAverage.toInt(), detailEntity.voteAverage.toInt())

        viewModel.getDetailMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(movieDummy)
    }
}