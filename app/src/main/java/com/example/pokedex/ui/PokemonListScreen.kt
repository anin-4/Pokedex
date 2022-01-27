package com.example.pokedex.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pokedex.presentation.pokemon_list.PokemonListViewModel

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel:PokemonListViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column{
            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "Pokemon", modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally))
            Spacer(modifier = Modifier.size(10.dp))
            PokemonListSection(navController)
        }
    }
}

@Composable
fun PokemonListSection(navController: NavController) {



}