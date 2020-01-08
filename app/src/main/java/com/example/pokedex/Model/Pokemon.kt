package com.example.pokedex.Model

import java.io.Serializable

data class Pokemon (
    var id:Int = 0,
    var name:String = "",
    var url:String = "",
    var urlFoto:String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" , //1.png
    var hora:Int = 0
    ) : Serializable

data class PokemonDetalle (
    var base_experience:Int = 0,
    var height:Int = 0,
    var weight:Int = 0
) : Serializable