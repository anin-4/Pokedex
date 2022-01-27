package com.example.pokedex.presentation

sealed class Screen(val route:String){
    object PokemonListScreen:Screen("PokemonListScreen")
    object PokemonDetailScreen:Screen("PokemonDetailScreen")
}
