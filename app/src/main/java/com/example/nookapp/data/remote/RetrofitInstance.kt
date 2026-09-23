package com.example.nookapp.data.remote


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * Project: nookApp
 * From: com.example.nookapp.data.remote
 * Created by: yoelc
 * On: 23/09/2026
 * All rights reserved: 2026
 */


object RetrofitInstance {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    val api: PokeApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)
    }
}