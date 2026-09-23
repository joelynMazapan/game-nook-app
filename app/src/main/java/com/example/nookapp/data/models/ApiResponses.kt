package com.example.nookapp.data.models

/**
 * Project: nookApp
 * From: com.example.nookapp.data.models
 * Created by: yoelc
 * On: 23/09/2026
 * All rights reserved: 2026
 */

data class PagedResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<NamedApiResource>
)

data class NamedApiResource(
    val name: String,
    val url: String
)

data class BerryModel(
    val name: String,
    val imageUrl: String
)

data class GameModel(
    val name: String,
    val generation: String
)

data class PokemonModel(
    val id: Int,
    val name: String,
    val imageUrl: String
)

data class VersionGroupDetail(
    val name: String,
    val generation: NamedApiResource
)