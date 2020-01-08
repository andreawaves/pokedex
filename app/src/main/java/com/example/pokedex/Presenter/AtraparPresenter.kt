package com.example.pokedex.Presenter

import android.util.Log
import android.widget.Adapter
import android.widget.ListAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.Model.Pokemon
import com.example.pokedex.Model.Respuesta
import com.example.pokedex.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
                        listaPokemons[i].id = i+1
                        listaPokemons[i].urlFoto = listaPokemons[i].urlFoto + (i+1) + ".png"
                        Log.e(TAG,"Pokemon: " + listaPokemons[i].urlFoto)
                    }
                    view.setAdapter(listaPokemons)

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