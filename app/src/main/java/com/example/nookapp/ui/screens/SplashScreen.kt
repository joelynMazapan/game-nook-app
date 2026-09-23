package com.example.nookapp.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.example.nookapp.R

/**
 * Project: nookApp
 * From: com.example.nookapp.ui.screens
 * Created by: yoelc
 * On: 21/09/2026
 * All rights reserved: 2026
 */

@Composable
fun SplashScreen(onNavigateToHome: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "spin")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "spinAngle"
    )

    LaunchedEffect(key1 = true) {
        delay(3000)
        onNavigateToHome()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_pokeball),
                contentDescription = "Pokébola de carga",
                modifier = Modifier
                    .size(150.dp)
                    .rotate(angle) // Aplicamos la rotación
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "PokeDex App",
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}