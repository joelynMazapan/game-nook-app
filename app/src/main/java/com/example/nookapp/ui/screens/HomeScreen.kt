package com.example.nookapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.nookapp.ui.components.CategoryCard

// Definimos una pequeña estructura de datos para nuestras categorías
data class Category(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateTo: (String) -> Unit
) {

    val categories = listOf(
        Category(
            title = "Pokédex",
            description = "Explora todos los personajes y sus estadísticas.",
            icon = Icons.Default.List,
            route = "pokedex_screen"
        ),
        Category(
            title = "Videojuegos",
            description = "Información sobre ediciones como Rubí, Zafiro, Esmeralda, etc.",
            icon = Icons.Default.PlayArrow,
            route = "games_screen"
        ),
        Category(
            title = "Bayas",
            description = "Consulta características de las bayas",
            icon = Icons.Default.Info,
            route = "berries_screen"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menú Principal", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories) { category ->
                CategoryCard(category = category, onNavigateTo = onNavigateTo)
            }
        }
    }
}

