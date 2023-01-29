package com.example.sigmamovieapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sigmamovieapp.databinding.ActivityMovieBinding
import com.example.sigmamovieapp.util.ViewModelFactory
import com.example.sigmamovieapp.view.DetailMovieViewModel.Companion.MOVIE_DETAIL

class MovieActivity : AppCompatActivity(), MovieAdapter.OnItemClickCallback {

    private lateinit var movieBinding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieBinding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(movieBinding.root)

        initView()
    }

    private fun initView() {
        val adapter = MovieAdapter()
        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        viewModel.getMovies().observe(this) { movieList ->
            adapter.setData(movieList)
            adapter.setOnItemClickCallback(this)
        }

        movieBinding.rvMovie.apply {
            layoutManager = LinearLayoutManager(this@MovieActivity)
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    override fun onItemClicked(id: String) {
        Intent(this, DetailMovieActivity::class.java).also { it ->
            it.apply {
                DetailMovieActivity.also {
                    putExtra(it.EXTRA_MOVIE, id)
                    putExtra(it.EXTRA_SELECT_MOVIE, MOVIE_DETAIL)
                    startActivity(this@apply)
                }
            }
        }
    }
}