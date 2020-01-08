package com.example.pokedex.Presenter

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.Model.Pokemon
import com.example.pokedex.Model.PokemonDetalle
import com.example.pokedex.View.AtraparActivity
import com.example.pokedex.View.DashboardActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CapturarPresenter (val view: CapturarPresenter.View) {
    var mContext : AppCompatActivity

    var retrofit: Retrofit
    private lateinit var service : ApiService
    val TAG = "POKEAPI"


    init {
        mContext = view.getContext()

        retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun capturar(pokemon: Pokemon) {
        buscarDatos(pokemon)
    }

    fun buscarDatos(pokemon: Pokemon) {
        service = retrofit.create(ApiService::class.java)
        service.obtenerPokemonDetalle(pokemon.id.toString()).enqueue(object :
            Callback<PokemonDetalle> {
            override fun onResponse(
                call: Call<PokemonDetalle>?,
                response: Response<PokemonDetalle>?
            ) {
                if (response!!.isSuccessful) {
                    val pokemonDetalle = response.body()
                    Log.e(TAG,"onResponse: " + pokemonDetalle.height)
                    almacenar(pokemon,pokemonDetalle)
                } else {
                    Log.e(TAG,"onResponse: " + response.errorBody())
                }
            }

            override fun onFailure(call: Call<PokemonDetalle>?, t: Throwable?) {
                Log.e(TAG,"onFailure: " + t!!.message)
            }
        })
    }

    fun almacenar(pokemon: Pokemon, pokemonDetalle: PokemonDetalle) {
        Log.e(TAG,"ATRAPADO: " + pokemonDetalle.base_experience)
    }

    fun irDashboard() {
        val intent = Intent(mContext, DashboardActivity::class.java)
        mContext.startActivity(intent)
    }

    interface View {
        fun getContext(): AppCompatActivity
    }
}