package com.example.sigmamovieapp.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by rarito
 */

data class MovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)