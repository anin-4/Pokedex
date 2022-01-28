package com.example.pokedex.presentation.pokemon_list

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.use_cases.GetPokemonList
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

    private fun loadNextPage(){}

    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }



}