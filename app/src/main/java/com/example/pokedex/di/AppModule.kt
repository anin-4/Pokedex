package com.example.pokedex.di

import com.example.pokedex.data.api.PokeApi
import com.example.pokedex.domain.repository.PokedexImplementation
import com.example.pokedex.domain.repository.PokedexRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

        @Singleton
        @Provides
        fun  provideRemoteDto() = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApi::class.java)

        @Singleton
        @Provides
        fun provideRepository(pokeApi:PokeApi):PokedexRepository = PokedexImplementation(pokeApi)
}