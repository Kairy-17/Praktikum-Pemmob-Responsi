package com.example.animeexplorer.data.model

import com.google.gson.annotations.SerializedName

data class AnimeListResponse(
    @SerializedName("data") val data: List<Anime>
)

data class AnimeDetailResponse(
    @SerializedName("data") val data: Anime
)

data class Anime(
    @SerializedName("mal_id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("score") val rating: Double? = 0.0,
    @SerializedName("year") val releaseYear: Int? = 0,
    @SerializedName("episodes") val episodes: Int? = 0,
    @SerializedName("synopsis") val synopsis: String? = null
)
