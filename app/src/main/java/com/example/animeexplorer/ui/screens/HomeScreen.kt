package com.example.animeexplorer.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animeexplorer.data.model.Anime
import com.example.animeexplorer.ui.state.AnimeListUiState
import com.example.animeexplorer.ui.viewmodel.AnimeViewModel
import com.example.animeexplorer.ui.viewmodel.AppViewModelProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToDetail: (String) -> Unit,
    viewModel: AnimeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.listUiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Anime Explorer") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            when (val state = uiState) {
                is AnimeListUiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is AnimeListUiState.Success -> {
                    AnimeList(animeList = state.data, onAnimeClick = onNavigateToDetail)
                }
                is AnimeListUiState.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Error: ${state.message}", color = MaterialTheme.colorScheme.error)
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(onClick = { viewModel.fetchAnimeList() }) {
                                Text("Retry")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AnimeList(animeList: List<Anime>, onAnimeClick: (String) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(animeList, key = { it.id }) { anime ->
            AnimeCard(anime = anime, onClick = { onAnimeClick(anime.id) })
        }
    }
}

@Composable
fun AnimeCard(anime: Anime, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = anime.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Rating: ${anime.rating}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Year: ${anime.releaseYear}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Episodes: ${anime.episodes}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
