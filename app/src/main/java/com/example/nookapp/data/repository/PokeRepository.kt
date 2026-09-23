package com.example.nookapp.data.repository

import com.example.nookapp.data.models.BerryModel
import com.example.nookapp.data.models.GameModel
import com.example.nookapp.data.models.NamedApiResource
import com.example.nookapp.data.models.PokemonModel
import com.example.nookapp.data.remote.RetrofitInstance
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class PokeRepository {
    private val api = RetrofitInstance.api

    // "https://pokeapi.co/api/v2/pokemon/25/" -> 25
    private fun NamedApiResource.extractId(): Int =
        url.trimEnd('/').substringAfterLast('/').toIntOrNull() ?: 0

    suspend fun getPokemonList(limit: Int = 40, offset: Int = 0): List<PokemonModel> {
        val response = api.getPokemonList(limit, offset)
        return response.results.map { resource ->
            val id = resource.extractId()
            PokemonModel(
                id = id,
                name = resource.name,
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
            )
        }
    }

    suspend fun getBerriesList(limit: Int = 20, offset: Int = 0): List<BerryModel> {
        val response = api.getBerriesList(limit, offset)
        return response.results.map { resource ->
            BerryModel(
                name = resource.name,
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/${resource.name}-berry.png"
            )
        }
    }

    // Cada version-group solo trae name+url en el listado, así que
    // pedimos el detalle de cada uno (en paralelo) para sacar la generación.
    suspend fun getGamesList(limit: Int = 40): List<GameModel> = coroutineScope {
        val response = api.getGamesList(limit)
        response.results
            .map { resource ->
                async {
                    val detail = api.getVersionGroupDetail(resource.name)
                    GameModel(
                        name = resource.name.replace("-", " ")
                            .replaceFirstChar { it.uppercase() },
                        generation = detail.generation.name.substringAfterLast("-").uppercase()
                    )
                }
            }
            .map { it.await() }
    }
}