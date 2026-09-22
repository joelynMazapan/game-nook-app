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
}