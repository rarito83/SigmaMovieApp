package com.example.sigmamovieapp.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sigmamovieapp.BuildConfig.IMAGE_URL
import com.example.sigmamovieapp.databinding.ItemMovieBinding
import com.example.sigmamovieapp.entity.Movie

/**
 * Created by rarito
 */

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val data = ArrayList<Movie>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    @SuppressLint("NotifyDataSetChanged")
    fun setData(movieList: List<Movie>) {
        if (movieList.isEmpty()) return
        with(this.data) {
            clear()
            addAll(movieList)
            notifyDataSetChanged()
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: MovieActivity) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val bind = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(bind)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: Movie) {
            with(binding) {

                movTitle.text = data.originalTitle

                movPopularity.text = data.popularity.toString() + " " + "Viewers"

                movRating.text = data.voteAverage.toString()

                movRelease.text = data.releaseDate

                Glide.with(itemView.context)
                    .load(IMAGE_URL + data.posterPath)
                    .into(imgPoster)

            }

            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(data.id.toString())
            }
        }
    }
}