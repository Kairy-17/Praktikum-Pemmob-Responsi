package com.example.animeexplorer.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeexplorer.data.repository.AnimeRepository
import com.example.animeexplorer.ui.state.AnimeDetailUiState
import com.example.animeexplorer.ui.state.AnimeListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeViewModel(private val repository: AnimeRepository) : ViewModel() {

    private val _listUiState = MutableStateFlow<AnimeListUiState>(AnimeListUiState.Loading)
    val listUiState: StateFlow<AnimeListUiState> = _listUiState.asStateFlow()

    private val _detailUiState = MutableStateFlow<AnimeDetailUiState>(AnimeDetailUiState.Loading)
    val detailUiState: StateFlow<AnimeDetailUiState> = _detailUiState.asStateFlow()

    init {
        fetchAnimeList()
    }

    fun fetchAnimeList() {
        viewModelScope.launch {
            _listUiState.value = AnimeListUiState.Loading
            try {
                val animeList = repository.getAnimeList()
                // Example of Collection Operations: Sort by rating descending
                val sortedList = animeList.sortedByDescending { it.rating }
                _listUiState.value = AnimeListUiState.Success(sortedList)
            } catch (e: Exception) {
                _listUiState.value = AnimeListUiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }

    fun fetchAnimeDetail(id: String) {
        viewModelScope.launch {
            _detailUiState.value = AnimeDetailUiState.Loading
            try {
                val anime = repository.getAnimeDetail(id)
                _detailUiState.value = AnimeDetailUiState.Success(anime)
            } catch (e: Exception) {
                _detailUiState.value = AnimeDetailUiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }
}
