package com.example.sigmamovieapp.network

import com.example.sigmamovieapp.BuildConfig.API_KEY
import com.example.sigmamovieapp.entity.DetailMovieResponse
import com.example.sigmamovieapp.entity.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by rarito
 */

interface CinemaService {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): Call<MovieResponse>

    @GET("movie/{id}")
    fun getDetailMovies(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = API_KEY
    ): Call<DetailMovieResponse>
}