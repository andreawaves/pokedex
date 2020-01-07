package com.example.pokedex.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.pokedex.Presenter.DashboardPresenter
import com.example.pokedex.Presenter.DashboardView
import com.example.pokedex.Presenter.LoginPresenter
import com.example.pokedex.R
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.my_toolbar.view.*


class DashboardActivity : AppCompatActivity(), DashboardView{

    var presenter : DashboardPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setSupportActionBar(toolbar as Toolbar)
        supportActionBar!!.title = null
        toolbar.titulo.text = "Dashboard"

        presenter = DashboardPresenter(this)
        btn_atrapar.setOnClickListener { presenter!!.irAtrapar()}
    }

    override fun getContext(): AppCompatActivity {
        return this
    }
}