package com.example.animeexplorer.ui.state

import com.example.animeexplorer.data.model.Anime

sealed class AnimeListUiState {
    data object Loading : AnimeListUiState()
    data class Success(val data: List<Anime>) : AnimeListUiState()
    data class Error(val message: String) : AnimeListUiState()
}

sealed class AnimeDetailUiState {
    data object Loading : AnimeDetailUiState()
    data class Success(val data: Anime) : AnimeDetailUiState()
    data class Error(val message: String) : AnimeDetailUiState()
}
