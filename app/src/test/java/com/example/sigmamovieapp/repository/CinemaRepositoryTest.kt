package com.example.sigmamovieapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sigmamovieapp.remote.RemoteDataSource
import com.example.sigmamovieapp.util.DataDummy
import com.example.sigmamovieapp.util.LiveDataUtility
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class CinemaRepositoryTest {

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val repository = FakeCinemaRepository(remote)

    private val moviesResponse = DataDummy.getMovieRemote()
    private val movieId = moviesResponse[0].id.toString()
    private val movieDetail = DataDummy.getDetailMovieRemote()


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun loadMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onAllMoviesReceived(
                moviesResponse
            )
            null
        }.`when`(remote).getMovies(any())

        val movieEntity = LiveDataUtility.getValue(repository.loadMovies())
        verify(remote).getMovies(any())
        assertNotNull(movieEntity)
        assertEquals(moviesResponse.size, movieEntity.size)
    }

    @Test
    fun loadMoviesDetails() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMoviesDetailsCallback)
                .onAllMoviesDetailsReceived(movieDetail)
            null
        }.`when`(remote).getMoviesDetail(eq(movieId), any())

        val detailMovie = LiveDataUtility.getValue(repository.loadMoviesDetails(movieId))
        verify(remote).getMoviesDetail(eq(movieId), any())
        assertNotNull(detailMovie)
        assertEquals(movieDetail.id, detailMovie.id)
    }
}