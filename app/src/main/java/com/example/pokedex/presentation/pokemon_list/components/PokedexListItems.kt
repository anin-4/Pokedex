package com.example.pokedex.presentation.pokemon_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.request.ImageRequest
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.presentation.pokemon_list.PokemonListViewModel
import com.google.accompanist.coil.CoilImage

@Composable
fun CircularProgressBar() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp)
    ){
        CircularProgressIndicator(
            color = MaterialTheme.colors.onPrimary
        )
    }

}

@Composable
fun ErrorState(msg:String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxHeight()
    ){
        Text(text = msg, style = MaterialTheme.typography.body1,color = MaterialTheme.colors.onError)
    }
}


@Composable
fun PokeCard(
    pokemon:Pokemon,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        dominantColor,
                        defaultDominantColor
                    )
                )
            )
            .clickable {

            }
    ){
        Column {
            CoilImage(
                request = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon.imgUrl)
                    .target {
                        viewModel.calcDominantColor(it) { color ->
                            dominantColor = color
                        }
                    }
                    .build(),
                contentDescription = pokemon.name,
                fadeIn = true,
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally)
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.scale(0.5f)
                )
            }
            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.body1,
                textAlign = Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowPokeCard() {
    PokeCard(pokemon = Pokemon(
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
        "bulbasaur",
        1
    ))
}