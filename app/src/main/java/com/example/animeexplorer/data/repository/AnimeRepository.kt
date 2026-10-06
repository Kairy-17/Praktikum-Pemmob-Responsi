package com.example.animeexplorer.data.repository

import com.example.animeexplorer.data.model.Anime
import com.example.animeexplorer.data.remote.AnimeApiService

class AnimeRepository(private val apiService: AnimeApiService) {
    suspend fun getAnimeList(): List<Anime> {
        return apiService.getAnimeList().data
    }
    
    suspend fun getAnimeDetail(id: String): Anime {
        return apiService.getAnimeDetail(id).data
    }
}
