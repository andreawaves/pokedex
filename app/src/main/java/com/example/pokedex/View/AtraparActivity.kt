package com.example.pokedex.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.pokedex.Model.Pokemon
import com.example.pokedex.Presenter.AtraparPresenter
import com.example.pokedex.Presenter.PokemonAdapter
import com.example.pokedex.Presenter.RegistroPresenter
import kotlinx.android.synthetic.main.activity_atrapar.*
import kotlinx.android.synthetic.main.my_toolbar.view.*

class AtraparActivity : AppCompatActivity(), AtraparPresenter.View {


    private var presenter: AtraparPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.pokedex.R.layout.activity_atrapar)

        setSupportActionBar(tb_cercanos as Toolbar)
        supportActionBar!!.title = null
        tb_cercanos.titulo.text = "Cercanos"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        presenter = AtraparPresenter(this)
        gv_cercanos.adapter = presenter!!.retornarAdapter()
    }

    override fun getContext(): AppCompatActivity {
        return this
    }
}
