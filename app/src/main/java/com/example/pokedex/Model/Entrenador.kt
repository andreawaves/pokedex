package com.example.pokedex.Model

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class Entrenador : RealmObject() {
    var usuario:String = ""
    var password:String = ""
}
