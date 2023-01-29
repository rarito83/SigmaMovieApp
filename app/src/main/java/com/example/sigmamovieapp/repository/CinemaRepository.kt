package com.example.sigmamovieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sigmamovieapp.entity.Detail
import com.example.sigmamovieapp.entity.DetailMovieResponse
import com.example.sigmamovieapp.entity.Movie
import com.example.sigmamovieapp.remote.DataSource
import com.example.sigmamovieapp.remote.RemoteDataSource

/**
 * Created by rarito
 */

class CinemaRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    DataSource {

    private lateinit var detailEntity: Detail

    companion object {
        @Volatile
        private var repository: CinemaRepository? = null

        fun getRepository(remoteDataSource: RemoteDataSource): CinemaRepository {
            return repository ?: synchronized(this) {
                CinemaRepository(remoteDataSource).apply {
                    repository = this
                }
            }
        }
    }

    override fun loadMovies(): LiveData<List<Movie>> {
        val getMovies = MutableLiveData<List<Movie>>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(moviesList: List<Movie>?) {
                val moviesEntity = ArrayList<Movie>()
                if (moviesList != null) {
                    for (movies in moviesList) {
                        movies.apply {
                            val moviesEntities = Movie(
                                id,
                                originalTitle,
                                popularity,
                                posterPath,
                                releaseDate,
                                voteAverage
                            )

                            moviesEntity.add(moviesEntities)
                        }
                    }
                    getMovies.postValue(moviesEntity)
                }
            }
        })

        return getMovies
    }

    override fun loadMoviesDetails(moviesId: String): LiveData<Detail> {
        val getMoviesDetail = MutableLiveData<Detail>()
        remoteDataSource.getMoviesDetail(
            moviesId,
            object : RemoteDataSource.LoadMoviesDetailsCallback {
                override fun onAllMoviesDetailsReceived(moviesDetails: DetailMovieResponse?) {
                    moviesDetails?.apply {
                        ArrayList<String>().also {
                            for (genre in genres) {
                                it.add(genre.name)
                            }

                            detailEntity = Detail(
                                id = id,
                                backdropPath = backdropPath,
                                posterPath = posterPath,
                                popularity = popularity,
                                originalTitle = originalTitle,
                                voteAverage = voteAverage,
                                releaseDate = releaseDate,
                                genres = it,
                                overview = overview
                            )
                        }
                        getMoviesDetail.postValue(detailEntity)
                    }
                }
            })
        return getMoviesDetail
    }
}