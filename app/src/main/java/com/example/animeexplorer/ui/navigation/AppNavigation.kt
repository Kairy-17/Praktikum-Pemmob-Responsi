package com.example.animeexplorer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.animeexplorer.ui.screens.AnimeDetailScreen
import com.example.animeexplorer.ui.screens.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onNavigateToDetail = { animeId ->
                    navController.navigate("detail/$animeId")
                }
            )
        }
        composable(
            route = "detail/{animeId}",
            arguments = listOf(navArgument("animeId") { type = NavType.StringType })
        ) { backStackEntry ->
            val animeId = backStackEntry.arguments?.getString("animeId") ?: return@composable
            AnimeDetailScreen(
                animeId = animeId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
