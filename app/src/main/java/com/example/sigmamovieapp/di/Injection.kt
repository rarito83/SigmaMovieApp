package com.example.sigmamovieapp.di

import com.example.sigmamovieapp.remote.RemoteDataSource.Companion.getRemoteDataSource
import com.example.sigmamovieapp.repository.CinemaRepository
import com.example.sigmamovieapp.repository.CinemaRepository.Companion.getRepository

/**
 * Created by rarito
 */

object Injection {

    fun provideRepository(): CinemaRepository {
        getRemoteDataSource().also {
            return getRepository(it)
        }
    }
}