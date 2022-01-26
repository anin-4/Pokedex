package com.example.pokedex.domain.use_cases

import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.repository.PokedexRepository
import com.example.pokedex.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPokemonList @Inject constructor(
    val repository: PokedexRepository
) {

    operator fun invoke(): Flow<Resource<List<Pokemon>>> = flow{
        emit(Resource.Loading<List<Pokemon>>())
        try {
            val pokemons = repository.getPokemonList(20,20).results.map{it.toPokemon()}
            emit(Resource.Success<List<Pokemon>>(data = pokemons))
        }catch (e:HttpException){
            emit(Resource.Error<List<Pokemon>>(msg ="Error http Exception"))
        }
        catch (e:IOException){
            emit(Resource.Error<List<Pokemon>>(msg = "Error IO Exception"))
        }
    }
}