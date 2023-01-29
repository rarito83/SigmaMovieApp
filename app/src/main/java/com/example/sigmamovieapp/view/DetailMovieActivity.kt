package com.example.sigmamovieapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.sigmamovieapp.BuildConfig.IMAGE_URL
import com.example.sigmamovieapp.databinding.ActivityDetailMovieBinding
import com.example.sigmamovieapp.entity.Detail
import com.example.sigmamovieapp.util.ViewModelFactory

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        initView()
    }

    private fun initView() {
        setupStatusBar()
        detailBinding.imgBackMov.setOnClickListener { finish() }

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        intent.extras.also {
            if (it != null) {
                val id = it.getString(EXTRA_MOVIE)
                val selectItem = it.getString(EXTRA_SELECT_MOVIE)
                Log.d("Detail Movie", "value intent id : $id")

                if (id != null && selectItem != null) {
                    viewModel.apply {
                        setDetailMovie(id, selectItem)
                        getDetailMovie().observe(this@DetailMovieActivity) { detail ->
                            setDetailMovie(detail)
                        }
                    }
                }
            }

        }
    }

    private fun setupStatusBar() {
        with(window) {
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    private fun setDetailMovie(movie: Detail) {
        val genre = movie.genres.toString().replace("[", "")
            .replace("]", "")

        detailBinding.tvTitleMov.text = movie.originalTitle
        detailBinding.tvDescriptionMov.text = movie.overview
        detailBinding.tvReleaseMov.text = movie.releaseDate
        detailBinding.tvGenresMov.text = genre
        detailBinding.tvRatingMov.text = movie.voteAverage.toString()

        Glide.with(this)
            .load(IMAGE_URL + movie.posterPath)
            .into(detailBinding.imgPosterMov)

        Glide.with(this)
            .load(IMAGE_URL + movie.backdropPath)
            .into(detailBinding.imgBackgroundMov)
    }

    companion object {
        const val EXTRA_MOVIE = "extra movie"
        const val EXTRA_SELECT_MOVIE = "extra select movie"
    }
}