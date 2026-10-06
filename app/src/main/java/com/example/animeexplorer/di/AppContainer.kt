package com.example.animeexplorer.di

import com.example.animeexplorer.data.remote.AnimeApiService
import com.example.animeexplorer.data.repository.AnimeRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppContainer {
    private const val BASE_URL = "https://api.tenrai.org/v1/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: AnimeApiService by lazy {
        retrofit.create(AnimeApiService::class.java)
    }

    val animeRepository: AnimeRepository by lazy {
        AnimeRepository(retrofitService)
    }
}
