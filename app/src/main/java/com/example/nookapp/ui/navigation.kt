package com.example.nookapp.ui

/**
 * Project: nookApp
 * From: com.example.nookapp.ui
 * Created by: yoelc
 * On: 22/09/2026
 * All rights reserved: 2026
 */

sealed class AppScreens(val route: String) {
    object Splash : AppScreens("splash_screen")
    object Home : AppScreens("home_screen")

    object Pokedex : AppScreens("pokedex_screen")
    object Games : AppScreens("games_screen")
    object Berries : AppScreens("berries_screen")

    object PokemonDetail : AppScreens("pokemon_detail_screen/{pokemonName}") {
        fun createRoute(pokemonName: String) = "pokemon_detail_screen/$pokemonName"
    }

    object GameDetail : AppScreens("game_detail_screen/{gameName}") {
        fun createRoute(gameName: String) = "game_detail_screen/$gameName"
    }
}