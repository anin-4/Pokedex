package com.example.pokedex.data.api

import com.example.pokedex.data.remote.Pokemon
import com.example.pokedex.data.remote.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset:Int,
        @Query("limit")limit:Int
    ):PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name")name:String
    ):Pokemon
}