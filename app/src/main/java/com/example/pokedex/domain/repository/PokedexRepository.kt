package com.example.pokedex.domain.repository


import com.example.pokedex.data.remote.Pokemon
import com.example.pokedex.data.remote.PokemonList





interface PokedexRepository{

    suspend fun getPokemonList(offset:Int,limit:Int):PokemonList

    suspend fun getPokemon(name:String): Pokemon
}