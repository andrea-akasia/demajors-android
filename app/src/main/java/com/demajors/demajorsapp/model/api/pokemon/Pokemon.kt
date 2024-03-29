package com.demajors.demajorsapp.model.api.pokemon

import com.google.gson.annotations.SerializedName

data class Pokemon(

    @SerializedName("name")
    val name: String = "",

    @SerializedName("url")
    val url: String? = null
) {

    fun getId(): Int {
        if (url != null) {
            return url.substringAfter("pokemon/").substringBefore("/").toInt()
        }
        return 0
    }

    fun getImage(): String {
        if (url != null) {
            val pokemonId = url.substringAfter("pokemon/").substringBefore("/")
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png"
        }
        return ""
    }
}
