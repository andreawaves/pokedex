package com.example.pokedex.Model

import java.io.Serializable
import io.realm.RealmObject
import io.realm.annotations.RealmClass

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

@RealmClass
open class PokemonCapturado : RealmObject() {
    var name:String = ""
    var urlFoto:String = ""
    var base_experience:Int = 0
    var height:Int = 0
    var weight:Int = 0

    var timestamp:String = ""
    var entrenador:String = ""
}