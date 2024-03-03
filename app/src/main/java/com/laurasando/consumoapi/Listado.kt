package com.laurasando.consumoapi

import com.google.gson.annotations.SerializedName

class Listado(@SerializedName("results") val pokemons: List<PokemonList>) {
}