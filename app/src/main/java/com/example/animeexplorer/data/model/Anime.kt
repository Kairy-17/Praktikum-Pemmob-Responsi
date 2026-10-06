package com.example.animeexplorer.data.model

import com.google.gson.annotations.SerializedName

data class Anime(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("rating") val rating: Double,
    @SerializedName("release_year") val releaseYear: Int,
    @SerializedName("episodes") val episodes: Int,
    @SerializedName("synopsis") val synopsis: String? = null
)
