package com.example.pokedex.Presenter

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.Model.Entrenador
import com.example.pokedex.Model.Pokemon
import com.example.pokedex.Model.PokemonCapturado
import com.example.pokedex.Model.PokemonDetalle
import com.example.pokedex.View.AtraparActivity
import com.example.pokedex.View.DashboardActivity
import io.realm.Realm
import io.realm.RealmConfiguration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class CapturarPresenter (val view: CapturarPresenter.View) {
    var mContext : AppCompatActivity

    //Retrofit
    var retrofit: Retrofit
    private lateinit var service : ApiService
    val TAG = "POKEAPI"

    //Realm
    var realm : Realm


    init {
        mContext = view.getContext()

        //Retrofit
        retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Realm
        Realm.init(view.getContext())
        val config = RealmConfiguration.Builder()
            .name("pokemones.realm").build()
        realm = Realm.getInstance (config)
        mContext = view.getContext()

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

        realm.beginTransaction()
        val pokemonCapturado = realm.createObject(PokemonCapturado::class.java)

        pokemonCapturado.name = pokemon.name
        pokemonCapturado.urlFoto = pokemon.urlFoto
        pokemonCapturado.base_experience = pokemonDetalle.base_experience
        pokemonCapturado.height = pokemonDetalle.height
        pokemonCapturado.weight = pokemonDetalle.weight

        val timeStamp: String = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date())

        pokemonCapturado.timestamp = timeStamp
        pokemonCapturado.entrenador = "1"

        realm.commitTransaction()

        Log.e(TAG,"ATRAPADO: " + pokemonCapturado.base_experience + " " + pokemonCapturado.timestamp)
        //view.mostrarMensaje("¡Pokemón capturado!")
    }

    fun irDashboard() {
        val intent = Intent(mContext, DashboardActivity::class.java)
        mContext.startActivity(intent)
    }

    interface View {
        fun getContext(): AppCompatActivity
    }
}