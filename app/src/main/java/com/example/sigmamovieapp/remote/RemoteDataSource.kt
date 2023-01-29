package com.example.sigmamovieapp.remote

import android.util.Log
import com.example.sigmamovieapp.entity.DetailMovieResponse
import com.example.sigmamovieapp.entity.Movie
import com.example.sigmamovieapp.entity.MovieResponse
import com.example.sigmamovieapp.network.ApiNetwork
import com.example.sigmamovieapp.util.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by rarito
 */

class RemoteDataSource {

    companion object {
        @Volatile
        private var remoteDataSource: RemoteDataSource? = null

        fun getRemoteDataSource(): RemoteDataSource {
            return remoteDataSource ?: synchronized(this) {
                RemoteDataSource().apply {
                    remoteDataSource = this
                }
            }
        }
    }

    fun getMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        ApiNetwork.apiService.getPopularMovies().enqueue(object :
            Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback.onAllMoviesReceived(response.body()?.results)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("Remote Data Source", "Movie Failed ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getMoviesDetail(id: String, callback: LoadMoviesDetailsCallback) {
        EspressoIdlingResource.increment()
        ApiNetwork.apiService.getDetailMovies(id).enqueue(object :
            Callback<DetailMovieResponse> {
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>
            ) {
                callback.onAllMoviesDetailsReceived(response.body())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.e("Remote Data Source", "Movie Detail Failed ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(moviesList: List<Movie>?)
    }


    interface LoadMoviesDetailsCallback {
        fun onAllMoviesDetailsReceived(moviesDetails: DetailMovieResponse?)
    }
}