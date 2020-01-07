package com.example.pokedex.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.pokedex.R
import kotlinx.android.synthetic.main.activity_atrapar.*
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.my_toolbar.view.*

class AtraparActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atrapar)

        setSupportActionBar(tb_cercanos as Toolbar)
        supportActionBar!!.title = null
        tb_cercanos.titulo.text = "Cercanos"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }
}
