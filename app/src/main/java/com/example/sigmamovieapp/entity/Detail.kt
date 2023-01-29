package com.example.sigmamovieapp.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by rarito
 */

data class Detail(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genres_ids")
    val genres: List<String>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_average")
    val voteAverage: Double
)
