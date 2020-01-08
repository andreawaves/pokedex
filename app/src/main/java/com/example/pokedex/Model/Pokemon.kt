package com.example.pokedex.Model

import io.realm.RealmObject
import io.realm.annotations.RealmClass


class Pokemon {
    var id:Int = 0
    var name:String = ""
    var url:String = ""
    var urlFoto:String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" //1.png
    var hora:Int = 0
}