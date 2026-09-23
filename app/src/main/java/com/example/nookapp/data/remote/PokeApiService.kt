package com.example.nookapp.data.remote

import com.example.nookapp.data.models.PagedResponse
import retrofit2.http.GET
import retrofit2.http.Query
/**
 * Project: nookApp
 * From: com.example.nookapp.data.remote
 * Created by: yoelc
 * On: 23/09/2026
 * All rights reserved: 2026
 */



interface PokeApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PagedResponse


    @GET("version-group")
    suspend fun getGamesList(
        @Query("limit") limit: Int = 40
    ): PagedResponse
}