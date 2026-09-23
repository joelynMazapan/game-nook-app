package com.example.nookapp.navigation

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nookapp.ui.AppScreens
import com.example.nookapp.ui.screens.*
import com.example.nookapp.ui.viewmodel.BerriesViewModel
import com.example.nookapp.ui.viewmodel.GamesViewModel
import com.example.nookapp.ui.viewmodel.PokedexViewModel
import com.example.nookapp.ui.viewmodel.UiState

/**
 * Project: nookApp
 * From: com.example.nookapp.navigation
 * Created by: yoelc
 * On: 21/09/2026
 * All rights reserved: 2026
 */

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Hoisteado aquí para compartirlo entre Pokedex y PokemonDetail
    val pokedexViewModel: PokedexViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = AppScreens.Splash.route
    ) {
        composable(AppScreens.Splash.route) {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(AppScreens.Home.route) {
                        popUpTo(AppScreens.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AppScreens.Home.route) {
            HomeScreen(onNavigateTo = { ruta -> navController.navigate(ruta) })
        }

        composable(AppScreens.Pokedex.route) {
            var searchText by remember { mutableStateOf("") }
            val state by pokedexViewModel.uiState.collectAsState()

            when (val s = state) {
                is UiState.Loading -> LoadingScreen()
                is UiState.Error -> ErrorScreen(s.message) { pokedexViewModel.loadPokemon() }
                is UiState.Success -> {
                    val filtered = s.data.filter { it.name.contains(searchText, ignoreCase = true) }
                    PokedexScreen(
                        pokemonList = filtered,
                        searchQuery = searchText,
                        onSearchQueryChange = { searchText = it },
                        onNavigateBack = { navController.popBackStack() },
                        onPokemonClick = { navController.navigate(AppScreens.PokemonDetail.createRoute(it.name)) }
                    )
                }
            }
        }

        composable(AppScreens.PokemonDetail.route) { backStackEntry ->
            val pokemonName = backStackEntry.arguments?.getString("pokemonName")
            val state by pokedexViewModel.uiState.collectAsState()

            val selectedPokemon = (state as? UiState.Success)?.data?.find { it.name == pokemonName }

            if (selectedPokemon != null) {
                PokemonDetailScreen(
                    pokemon = selectedPokemon,
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }

        composable(AppScreens.Games.route) {
            var searchText by remember { mutableStateOf("") }
            val gamesViewModel: GamesViewModel = viewModel()
            val state by gamesViewModel.uiState.collectAsState()

            when (val s = state) {
                is UiState.Loading -> LoadingScreen()
                is UiState.Error -> ErrorScreen(s.message) { gamesViewModel.loadGames() }
                is UiState.Success -> {
                    val filtered = s.data.filter { it.name.contains(searchText, ignoreCase = true) }
                    GamesScreen(
                        games = filtered,
                        searchQuery = searchText,
                        onSearchQueryChange = { searchText = it },
                        onNavigateBack = { navController.popBackStack() },
                        onGameClick = { selectedGame -> // <-- Agregamos el evento de clic en inglés
                            navController.navigate(AppScreens.GameDetail.createRoute(selectedGame.name))
                        }
                    )
                }
            }
        }

        composable(AppScreens.Berries.route) {
            var searchText by remember { mutableStateOf("") }
            val berriesViewModel: BerriesViewModel = viewModel()
            val state by berriesViewModel.uiState.collectAsState()

            when (val s = state) {
                is UiState.Loading -> LoadingScreen()
                is UiState.Error -> ErrorScreen(s.message) { berriesViewModel.loadBerries() }
                is UiState.Success -> {
                    val filtered = s.data.filter { it.name.contains(searchText, ignoreCase = true) }
                    BerriesScreen(
                        berries = filtered,
                        searchQuery = searchText,
                        onSearchQueryChange = { searchText = it },
                        onNavigateBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}