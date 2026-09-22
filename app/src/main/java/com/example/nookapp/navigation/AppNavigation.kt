package com.example.nookapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nookapp.ui.AppScreens
import com.example.nookapp.ui.screens.HomeScreen
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
    // El controlador de navegación que recuerda el estado
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.Splash.route // La app inicia en el Splash
    ) {
        // Pantalla 1: Splash Screen
        composable(AppScreens.Splash.route) {
            SplashScreen(
                onNavigateToHome = {
                    // Navegamos al Home
                    navController.navigate(AppScreens.Home.route) {
                        // Limpiamos el backstack para no volver al Splash al presionar "Atrás"
                        popUpTo(AppScreens.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Pantalla 2: Home Screen (Menú principal)
        composable(AppScreens.Home.route) {
            HomeScreen()
        }
    }
}