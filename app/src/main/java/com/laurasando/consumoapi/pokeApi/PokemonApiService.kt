package com.laurasando.consumoapi.pokeApi


import com.laurasando.consumoapi.Listado
import com.laurasando.consumoapi.PokemonList
import com.laurasando.consumoapi.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface PokemonApiService {
    @GET("pokemon/{id}")

    fun getPokemonById(@Path("id") id: Int): Call<Pokemon>

    @GET("pokemon/")
    fun getPokemons(): Call<Listado>

}