package com.example.pokedex.presentation

import androidx.lifecycle.ViewModel
import com.example.pokedex.domain.use_cases.GetPokemonList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PokemonListViewModel @Inject constructor(
    val getPokemonList: GetPokemonList
):ViewModel() {



}