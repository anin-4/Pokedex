package com.example.pokedex.data.remote

import com.example.pokedex.domain.model.Pokemon

data class Result(
    val name: String,
    val url: String
){
    fun toPokemon():Pokemon{
        val num = if(url.endsWith("/")){
            url.dropLast(1).takeLastWhile { it.isDigit() }
        }else{
            url.takeLastWhile { it.isDigit() }
        }
        return Pokemon(
            imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${num}.png",
            name = name,
            number = num.toInt()
        )
    }
}