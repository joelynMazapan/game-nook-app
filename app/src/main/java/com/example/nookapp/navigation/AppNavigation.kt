package com.example.nookapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nookapp.data.models.BerryModel
import com.example.nookapp.data.models.GameModel
import com.example.nookapp.data.models.PokemonModel
import com.example.nookapp.ui.AppScreens
import com.example.nookapp.ui.screens.BerriesScreen
import com.example.nookapp.ui.screens.GameDetailScreen
import com.example.nookapp.ui.screens.GamesScreen
import com.example.nookapp.ui.screens.HomeScreen
import com.example.nookapp.ui.screens.PokedexScreen
import com.example.nookapp.ui.screens.PokemonDetailScreen
import com.example.nookapp.ui.screens.SplashScreen

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

    NavHost(
        navController = navController,
        startDestination = AppScreens.Splash.route
    ) {
        composable(AppScreens.Splash.route) {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(AppScreens.Home.route) {
                        popUpTo(AppScreens.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(AppScreens.Home.route) {
            HomeScreen(
                onNavigateTo = { ruta ->
                    navController.navigate(ruta)
                }
            )
        }

        composable(AppScreens.Pokedex.route) {
            var searchText by remember { mutableStateOf("") }
            val mockPokemon = listOf(
                PokemonModel(1, "bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
                PokemonModel(4, "charmander", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png"),
                PokemonModel(7, "squirtle", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png"),
                PokemonModel(25, "pikachu", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png")
            )
            val filteredPokemon = mockPokemon.filter { it.name.contains(searchText, ignoreCase = true) }

            PokedexScreen(
                pokemonList = filteredPokemon,
                searchQuery = searchText,
                onSearchQueryChange = { newText -> searchText = newText },
                onNavigateBack = { navController.popBackStack() },
                onPokemonClick = { selectedPokemon ->
                    navController.navigate(AppScreens.PokemonDetail.createRoute(selectedPokemon.name))
                }
            )
        }

        composable(AppScreens.PokemonDetail.route) { backStackEntry ->
            val pokemonName = backStackEntry.arguments?.getString("pokemonName")

            val mockPokemon = listOf(
                PokemonModel(1, "bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
                PokemonModel(4, "charmander", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png"),
                PokemonModel(7, "squirtle", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png"),
                PokemonModel(25, "pikachu", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png")
            )

            val selectedPokemon = mockPokemon.find { it.name == pokemonName }

            if (selectedPokemon != null) {
                PokemonDetailScreen(
                    pokemon = selectedPokemon,
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }

        composable(AppScreens.Games.route) {
            var searchText by remember { mutableStateOf("") }
            val mockGames = listOf(
                GameModel("Pokémon Rubí", "III"),
                GameModel("Pokémon Zafiro", "III"),
                GameModel("Pokémon Esmeralda", "III"),
                GameModel("Pokémon Mundo Misterioso: Equipo de Rescate Rojo", "III")
            )

            val filteredGames = mockGames.filter {
                it.name.contains(searchText, ignoreCase = true)
            }

            GamesScreen(
                games = filteredGames,
                searchQuery = searchText,
                onSearchQueryChange = { newText -> searchText = newText },
                onNavigateBack = { navController.popBackStack() },
                onGameClick = { selectedGame ->
                    navController.navigate(AppScreens.GameDetail.createRoute(selectedGame.name))
                }
            )
        }

        composable(AppScreens.GameDetail.route) { backStackEntry ->
            val gameName = backStackEntry.arguments?.getString("gameName")

            val mockGames = listOf(
                GameModel("Pokémon Rubí", "III"),
                GameModel("Pokémon Zafiro", "III"),
                GameModel("Pokémon Esmeralda", "III"),
                GameModel("Pokémon Mundo Misterioso: Equipo de Rescate Rojo", "III")
            )

            val selectedGame = mockGames.find { it.name == gameName }

            if (selectedGame != null) {
                GameDetailScreen(
                    game = selectedGame,
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }

        composable(AppScreens.Berries.route) {
            var searchText by remember { mutableStateOf("") }

            val mockBerries = listOf(
                BerryModel("Cheri", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/cheri-berry.png"),
                BerryModel("Chesto", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/chesto-berry.png"),
                BerryModel("Pecha", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/pecha-berry.png")
            )

            val filteredBerries = mockBerries.filter {
                it.name.contains(searchText, ignoreCase = true)
            }

            BerriesScreen(
                berries = filteredBerries,
                searchQuery = searchText,
                onSearchQueryChange = { newText -> searchText = newText },
                onNavigateBack = { navController.popBackStack() }
            )
        }



    }
}
