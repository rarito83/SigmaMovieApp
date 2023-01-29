package com.example.sigmamovieapp.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by rarito
 */

data class GenreResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
