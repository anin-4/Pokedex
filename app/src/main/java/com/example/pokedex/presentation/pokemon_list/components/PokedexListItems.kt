package com.example.pokedex.presentation.pokemon_list.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
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


@ExperimentalCoilApi
@Composable
fun PokeCard(
    modifier: Modifier = Modifier,
    pokemon:Pokemon
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.White,
                        Color.LightGray
                    )
                )
            )
            .clickable {

            }
    ){
        Column {

            val painter = rememberImagePainter(data = pokemon.imgUrl)
            Image(
                painter = painter,
                contentDescription = pokemon.name,
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally)

            )
            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.body1,
                textAlign = Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
fun PokedexRow(
    index:Int,
    pokemons:List<Pokemon>
) {
    val currEle = pokemons[2*index]
    Column{
        Row{
            Spacer(modifier = Modifier.width(16.dp))
            PokeCard(modifier = Modifier.weight(1f), pokemon = currEle)
            Spacer(modifier = Modifier.width(16.dp))
            if(pokemons.size > 2*index){
                PokeCard(modifier = Modifier.weight(1f),pokemon = pokemons[2*index+1])
                Spacer(modifier = Modifier.width(16.dp))
            }else{
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
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