package com.example.animeexplorer.data.remote

import com.example.animeexplorer.data.model.Anime
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeApiService {
    @GET("anime")
    suspend fun getAnimeList(): List<Anime>

    @GET("anime/{id}")
    suspend fun getAnimeDetail(@Path("id") id: String): Anime
}
