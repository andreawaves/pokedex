package com.example.pokedex.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.pokedex.Model.PokemonCapturado
import com.example.pokedex.Presenter.PokedexPresenter
import com.example.pokedex.R
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_pokedex.*
import kotlinx.android.synthetic.main.my_toolbar.view.*

class PokedexActivity : AppCompatActivity(), PokedexPresenter.View {

    var presenter : PokedexPresenter? = null
    var pokemones : RealmResults<PokemonCapturado>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)

        setSupportActionBar(tb_pokedex as Toolbar)
        supportActionBar!!.title = null
        tb_pokedex.titulo.text = "Pókedex"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        presenter = PokedexPresenter(this)

        btn_menu.setOnClickListener { presenter!!.irDashboard() }

        pokemones = presenter!!.obtenerPokemones()

        if (pokemones == null) {
            l_pokedex.visibility = View.INVISIBLE
            Toast.makeText(this, "No tienes pokemones :c", Toast.LENGTH_LONG).show()

        } else {
            mostrarPokemon(pokemones!![0]!!)
            btn_anterior.setOnClickListener {actualizarInfo(-1)}
            btn_siguiente.setOnClickListener {actualizarInfo(1)}
        }
    }

    fun actualizarInfo(index:Int){
        val tam = pokemones!!.size
        if (index>=tam || index == -1){
            Toast.makeText(this, "Llegaste a tu límite", Toast.LENGTH_LONG).show()
        } else {
            mostrarPokemon(pokemones!![index]!!)
            btn_anterior.setOnClickListener {actualizarInfo(index-1)}
            btn_siguiente.setOnClickListener {actualizarInfo(index+1)}
        }
    }

    fun mostrarPokemon(pokemon: PokemonCapturado){
        tv_pokemonName.text = pokemon.name
        tv_experiencia.text = pokemon.base_experience.toString()
        tv_height.text = pokemon.height.toString()
        tv_weight.text = pokemon.weight.toString()
        tv_timestamp.text = pokemon.timestamp
        Glide.with(this)
            .load(pokemon.urlFoto)
            .into(iv_pokemonPhoto)
    }

    override fun getContext(): AppCompatActivity {
        return this
    }
}
