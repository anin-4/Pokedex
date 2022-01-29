package com.example.pokedex.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pokedex.presentation.pokemon_list.PokemonListViewModel
import com.example.pokedex.R
import com.example.pokedex.presentation.pokemon_list.PokemonListState
import com.example.pokedex.presentation.pokemon_list.components.CircularProgressBar
import com.example.pokedex.presentation.pokemon_list.components.ErrorState
import com.example.pokedex.presentation.pokemon_list.components.PokedexRow

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel:PokemonListViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val currPage  = remember {
        mutableStateOf(1)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column{
            Spacer(modifier = Modifier.size(20.dp))
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Pokemon Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.size(10.dp))
            if(state.isLoading){
                CircularProgressBar()
            }
            else if(state.error.isNotEmpty()){
                ErrorState(state.error)
            }
            else{
                LazyColumn(modifier = Modifier.fillMaxSize()){
                    val rows:Int = (state.pokemonList.size+1)/2
                    items(rows){ index ->
                        if(index >= rows-2){
                            viewModel.loadNextPage(currPage.value)
                            currPage.value+=1
                        }
                        PokedexRow(index,state.pokemonList)
                    }

                }
            }
        }
    }
}

