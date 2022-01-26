package com.example.pokedex.data.remote

import com.google.gson.annotations.SerializedName

data class GenerationV(
    @SerializedName("blackWhite")
    val blackWhite: BlackWhite
)