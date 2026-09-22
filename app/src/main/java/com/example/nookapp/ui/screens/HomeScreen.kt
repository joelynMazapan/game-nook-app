package com.example.nookapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Project: nookApp
 * From: com.example.nookapp.ui.screens
 * Created by: yoelc
 * On: 22/09/2026
 * All rights reserved: 2026
 */
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "¡Bienvenido a la PokeDex!")
    }
}