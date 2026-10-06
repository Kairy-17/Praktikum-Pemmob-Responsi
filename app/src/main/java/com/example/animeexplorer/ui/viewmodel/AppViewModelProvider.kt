package com.example.animeexplorer.ui.viewmodel

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.animeexplorer.di.AppContainer

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            AnimeViewModel(AppContainer.animeRepository)
        }
    }
}
