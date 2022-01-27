package com.example.pokedex.presentation.pokemon_list

import com.example.pokedex.domain.model.Pokemon

data class PokemonListState(
    val isLoading:Boolean = false,
    val pokemonList:List<Pokemon> = emptyList(),
    val error:String =""
)