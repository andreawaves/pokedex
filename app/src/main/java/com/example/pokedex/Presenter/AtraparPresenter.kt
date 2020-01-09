package com.example.pokedex.Presenter

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.Model.Pokemon
import com.example.pokedex.Model.Respuesta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.icu.util.Calendar
import android.widget.Toast


class AtraparPresenter (view:View) {
    var mContext : AppCompatActivity
    var retrofit: Retrofit
    private lateinit var service : ApiService
    val TAG = "POKEAPI"
    val view = view

    init {
        mContext = view.getContext()

        retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun obtenerPokemones() {
        service = retrofit.create(ApiService::class.java)
        service.obtenerListaPokemon().enqueue(object : Callback<Respuesta> {
            override fun onResponse(
                call: Call<Respuesta>?,
                response: Response<Respuesta>?
            ) {
                if (response!!.isSuccessful()){
                    val respuesta = response.body()
                    var listaPokemons = respuesta.results

                    for (i in 0 until listaPokemons.size){
                        Log.e(TAG,"Pokemon: " + listaPokemons[i].name)
                        listaPokemons[i].name = listaPokemons[i].name.capitalize()
                        listaPokemons[i].id = i+1
                        listaPokemons[i].urlFoto = listaPokemons[i].urlFoto + (i+1) + ".png"
                        Log.e(TAG,"Pokemon: " + listaPokemons[i].urlFoto)

                        //Spawn
                        when (listaPokemons[i].id) {
                            1 -> listaPokemons[i].hora = 9
                            2 -> listaPokemons[i].hora = 7
                            3 -> listaPokemons[i].hora = 11
                            4 -> listaPokemons[i].hora = 8
                            5 -> listaPokemons[i].hora = 7
                            6 -> listaPokemons[i].hora = 1
                            7 -> listaPokemons[i].hora = 4
                            8 -> listaPokemons[i].hora = 7
                            9 -> listaPokemons[i].hora = 12
                            10 -> listaPokemons[i].hora = 4
                            11 -> listaPokemons[i].hora = 2
                            12 -> listaPokemons[i].hora = 3
                            13 -> listaPokemons[i].hora = 2
                            14 -> listaPokemons[i].hora = 2
                            15 -> listaPokemons[i].hora = 4
                            16 -> listaPokemons[i].hora = 1
                            17 -> listaPokemons[i].hora = 1
                            18 -> listaPokemons[i].hora = 1
                            19 -> listaPokemons[i].hora = 6 //1
                            20 -> listaPokemons[i].hora = 6 //1
                            else -> listaPokemons[i].hora = 0
                        }
                    }

                    //Filtrando x hora
                    var listaPokemonsFinal = ArrayList<Pokemon>()

                    val rightNow = Calendar.getInstance()
                    val currentHour = rightNow.get(Calendar.HOUR)
                    Log.e(TAG,"CURRENT HOUR: " + currentHour )

                    for (i in 0..19) {
                        if (listaPokemons[i].hora == currentHour){
                            listaPokemonsFinal.add(listaPokemons[i])
                        }
                    }

                    if (listaPokemonsFinal.isEmpty()) {
                        Toast.makeText(mContext, "No hay pokemones :C", Toast.LENGTH_LONG).show()
                    }
                    view.setAdapter(listaPokemonsFinal)

                } else {
                    Log.e(TAG,"onResponse: " + response.errorBody())
                }
            }
            override fun onFailure(call: Call<Respuesta>?, t: Throwable?) {
                Log.e(TAG,"onFailure: " + t!!.message)
            }
        })

    }


    interface View {
        fun getContext(): AppCompatActivity
        fun setAdapter(pokemonList : ArrayList<Pokemon>)
    }
}