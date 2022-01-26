package com.example.pokedex.domain.repository

import com.example.pokedex.data.api.PokeApi
import com.example.pokedex.data.remote.Pokemon
import com.example.pokedex.data.remote.PokemonList
import javax.inject.Inject

class PokedexImplementation @Inject constructor(
    private val pokeApi: PokeApi
):PokedexRepository {
    override suspend fun getPokemonList(offset:Int,limit:Int): PokemonList {
        return pokeApi.getPokemonList(offset, limit)
    }

    override suspend fun getPokemon(name:String): Pokemon {
        return pokeApi.getPokemon(name)
    }


}