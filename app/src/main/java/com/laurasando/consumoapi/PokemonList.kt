package com.laurasando.consumoapi

import com.google.gson.annotations.SerializedName

class PokemonList(@SerializedName("name") val name: String,
                  @SerializedName("url") val url: String) {
}