package com.example.pokedex.presentation.pokemon_list


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.use_cases.GetPokemonList
import com.example.pokedex.utils.Constants.PAGE_SIZE
import com.example.pokedex.utils.Constants.TAG
import com.example.pokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonList: GetPokemonList
):ViewModel() {

    private val _state = mutableStateOf(PokemonListState())
    val state: State<PokemonListState> = _state


    init{
        getPokemons()
    }

    private fun getPokemons(offset:Int=0,limit:Int=20){
        getPokemonList(offset,limit).onEach {
            when(it){
                is Resource.Success -> {
                    _state.value = PokemonListState(pokemonList = it.data?: emptyList())
                    Log.d(TAG, "getPokemons: ${_state.value.pokemonList}")
                }
                is Resource.Loading -> {
                    _state.value = PokemonListState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = PokemonListState(error = it.msg?:"An unexpected Error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loadNextPage(currentPage:Int) {
        getPokemonList(currentPage * PAGE_SIZE, PAGE_SIZE).onEach {
            when (it) {
                is Resource.Success -> {
                    val currentList = _state.value.pokemonList
                    val newList = currentList + (it.data as List<Pokemon>)
                    _state.value = PokemonListState(pokemonList = newList)
                }
                is Resource.Error -> {
                    _state.value =
                        PokemonListState(error = it.msg ?: "An unexpected Error occurred")
                }

            }
        }.launchIn(viewModelScope)


    }

    }