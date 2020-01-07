package com.example.pokedex.Presenter

import android.widget.Adapter
import android.widget.ListAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.Model.Pokemon
import com.example.pokedex.R


class AtraparPresenter (view:View) {
    var mContext : AppCompatActivity

    init {
        mContext = view.getContext()
    }


    fun retornarAdapter() : ListAdapter{
        var adapter: PokemonAdapter? = null
        var pokemonList = ArrayList<Pokemon>()
        for (i in 1..10) {
            var pokemon = Pokemon()
            pokemon.nombre = "Pokemon N°${i}"
            pokemonList.add(pokemon)
        }

        adapter = PokemonAdapter(mContext, pokemonList)
        return adapter
    }

    interface View {
        fun getContext(): AppCompatActivity
    }
}