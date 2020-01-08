package com.example.pokedex.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.pokedex.Model.Pokemon
import com.example.pokedex.Presenter.AtraparPresenter
import com.example.pokedex.Presenter.CapturarPresenter
import com.example.pokedex.R
import kotlinx.android.synthetic.main.activity_atrapar.*
import kotlinx.android.synthetic.main.activity_capturar.*
import kotlinx.android.synthetic.main.my_toolbar.view.*
import kotlinx.android.synthetic.main.pokemon_item.view.*

class CapturarActivity : AppCompatActivity(), CapturarPresenter.View {

    private var presenter: CapturarPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capturar)

        presenter = CapturarPresenter(this)

        setSupportActionBar(tb_atraparlo as Toolbar)
        supportActionBar!!.title = null
        tb_atraparlo.titulo.text = "¡Atrápalo!"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val pokemon = intent.getSerializableExtra("pokemon") as Pokemon

        tv_name.text = pokemon.name

        Glide.with(this)
            .load(pokemon.urlFoto)
            .into(iv_photo)

        iv_photo.setOnClickListener { presenter!!.capturar(pokemon) }
    }

    override fun getContext(): AppCompatActivity {
        return this
    }

}
