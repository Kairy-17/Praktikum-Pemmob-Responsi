package com.example.animeexplorer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animeexplorer.ui.state.AnimeDetailUiState
import com.example.animeexplorer.ui.viewmodel.AnimeViewModel
import com.example.animeexplorer.ui.viewmodel.AppViewModelProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailScreen(
    animeId: String,
    onNavigateBack: () -> Unit,
    viewModel: AnimeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    // Fetch detail when screen is opened
    LaunchedEffect(animeId) {
        viewModel.fetchAnimeDetail(animeId)
    }

    val uiState by viewModel.detailUiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Anime") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Text(text = "<-")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            when (val state = uiState) {
                is AnimeDetailUiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is AnimeDetailUiState.Success -> {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = state.data.title, style = MaterialTheme.typography.headlineMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Rating: ${state.data.rating ?: "-"}", style = MaterialTheme.typography.titleMedium)
                        Text(text = "Tahun Rilis: ${state.data.releaseYear ?: "-"}", style = MaterialTheme.typography.titleMedium)
                        Text(text = "Jumlah Episode: ${state.data.episodes ?: "-"}", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Sinopsis", style = MaterialTheme.typography.titleLarge)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = state.data.synopsis ?: "Sinopsis tidak tersedia.",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                is AnimeDetailUiState.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Terjadi Kesalahan: ${state.message}", color = MaterialTheme.colorScheme.error)
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(onClick = { viewModel.fetchAnimeDetail(animeId) }) {
                                Text("Coba Lagi")
                            }
                        }
                    }
                }
            }
        }
    }
}
