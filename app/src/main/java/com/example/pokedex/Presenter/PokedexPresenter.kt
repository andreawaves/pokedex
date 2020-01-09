package com.example.pokedex.Presenter

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.Model.Entrenador
import com.example.pokedex.Model.PokemonCapturado
import com.example.pokedex.View.DashboardActivity
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults

class PokedexPresenter (val view: PokedexPresenter.View) {

    var mContext : AppCompatActivity
    var realm : Realm

    init {
        mContext = view.getContext()

        Realm.init(mContext)
        val config = RealmConfiguration.Builder()
            .name("pokemones.realm").build()
        realm = Realm.getInstance(config)
    }

    fun obtenerPokemones():RealmResults<PokemonCapturado>?{

        val sharedpreferences = mContext.getSharedPreferences("login", Context.MODE_PRIVATE)
        val entrenador = sharedpreferences.getString("userID", "")!!

        val pokemones = realm.where(PokemonCapturado::class.java).equalTo("entrenador",entrenador).findAll()
        if (pokemones.isEmpty()){
            return null
        }
        else {
            return pokemones
        }

    }

    fun irDashboard() {
        val intent = Intent(mContext, DashboardActivity::class.java)
        mContext.startActivity(intent)
    }


    interface View {
        fun getContext(): AppCompatActivity
    }
}