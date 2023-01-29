package com.example.sigmamovieapp.util

import com.example.sigmamovieapp.entity.Detail
import com.example.sigmamovieapp.entity.DetailMovieResponse
import com.example.sigmamovieapp.entity.GenreResponse
import com.example.sigmamovieapp.entity.Movie

object DataDummy {

    fun getMovies(): List<Movie> {
        ArrayList<Movie>().also {
            it.apply {
                add(
                    Movie(
                        578,
                        "Jaws",
                        26.63,
                        "https://image.tmdb.org/t/p/w500/s2xcqSFfT6F7ZXHxowjxfG0yisT.jpg",
                        "1975-06-18",
                        7.6
                    )
                )
                add(
                    Movie(
                        460,
                        "Alles auf Zucker!",
                        3.335,
                        "https://image.tmdb.org/t/p/w500/sDAVHwXZI3yMSBOqjZGtiEyfhQt.jpg",
                        "2004-12-31",
                        6.6
                    )
                )
                add(
                    Movie(
                        632,
                        "Stalag 17",
                        8.143,
                        "https://image.tmdb.org/t/p/w500/lfve9FDKjT7JPbWI9NCs5340F79.jpg",
                        "1953-05-29",
                        7.7
                    )
                )
            }
            return it
        }
    }

    fun getMovieDetail(): Detail {
        return Detail(
            "https://image.tmdb.org/t/p/w500/fPGeS6jgdLovQAKunNHX8l0avCy.jpg",
            listOf("Action", "Thriller", "War"),
            567189,
            "Tom Clancy's Without Remorse",
            "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
            0.6,
            "https://image.tmdb.org/t/p/w500/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
            "2021-04-29",
            7.2
        )
    }

    fun getMovieRemote(): List<Movie> {
        ArrayList<Movie>().also {
            it.apply {
                add(
                    Movie(
                        id = 578701,
                        originalTitle = "Those Who Wish Me Dead",
                        popularity = 921.595,
                        posterPath = "https://image.tmdb.org/t/p/w500/xCEg6KowNISWvMh8GvPSxtdf9TO.jpg",
                        releaseDate = "2021-05-05",
                        voteAverage = 7.0,
                    )
                )
                add(
                    Movie(
                        id = 460465,
                        originalTitle = "Mortal Kombat",
                        popularity = 1373.864,
                        posterPath = "https://image.tmdb.org/t/p/w500/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                        releaseDate = "2021-04-07",
                        voteAverage = 7.6,
                    )
                )
                add(
                    Movie(
                        id = 632357,
                        originalTitle = "The Unholy",
                        popularity = 1026.613,
                        posterPath = "https://image.tmdb.org/t/p/w500/b4gYVcl8pParX8AjkN90iQrWrWO.jpg",
                        releaseDate = "2021-03-31",
                        voteAverage = 7.3,
                    )
                )
            }

            return it
        }
    }


    fun getDetailMovieRemote(): DetailMovieResponse {
        return DetailMovieResponse(
            id = 567189,
            backdropPath = "https://image.tmdb.org/t/p/w500/fPGeS6jgdLovQAKunNHX8l0avCy.jpg",
            genres = listOf(
                GenreResponse(
                    id = 28,
                    name = "Action"
                ),
                GenreResponse(
                    id = 12,
                    name = "Adventure"
                ),
                GenreResponse(
                    id = 53,
                    name = "Thriller"
                ),
                GenreResponse(
                    id = 10752,
                    name = "War"
                )
            ),
            originalTitle = "Tom Clancy's Without Remorse",
            overview = "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
            popularity = 1130.112,
            posterPath = "https://image.tmdb.org/t/p/w500/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
            releaseDate = "2021-04-29",
            voteAverage = 7.3
        )
    }
}