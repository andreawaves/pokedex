package com.example.pokedex.Presenter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.pokedex.Model.Pokemon
import com.example.pokedex.R
import kotlinx.android.synthetic.main.pokemon_item.view.*

class PokemonAdapter : BaseAdapter {
    var pokemonList = ArrayList<Pokemon>()
    var mContext : Context? = null

    constructor(mContext:Context, pokemonList:ArrayList<Pokemon>):super(){
        this.pokemonList = pokemonList
        this.mContext = mContext
    }

    override fun getCount(): Int {
        return pokemonList.size
    }

    override fun getItem(position: Int): Any {
        return pokemonList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val pokemon = this.pokemonList[position]

        var inflator = mContext!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var pokemonView = inflator.inflate(R.layout.pokemon_item, null)

        pokemonView.tv_nombre.text = pokemon.name

        Glide.with(mContext!!)
            .load(pokemon.urlFoto)
            .into(pokemonView.iv_foto)

        return pokemonView
    }

}
