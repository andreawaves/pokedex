package com.example.pokedex.Presenter

import com.example.pokedex.Model.Respuesta
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon")
    fun obtenerListaPokemon(): Call<Respuesta>

}