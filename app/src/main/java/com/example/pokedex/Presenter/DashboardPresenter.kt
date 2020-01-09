package com.example.pokedex.Presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.pokedex.View.AtraparActivity
import com.example.pokedex.View.PokedexActivity
import com.example.pokedex.View.RegistroActivity

class DashboardPresenter (val view: DashboardPresenter.View) {
    var mContext : AppCompatActivity

    init {
        mContext = view.getContext()
    }

    fun irAtrapar() {
        val intent = Intent(mContext, AtraparActivity::class.java)
        mContext.startActivity(intent)
    }

    fun irPokedex() {
        val intent = Intent(mContext, PokedexActivity::class.java)
        mContext.startActivity(intent)
    }

    interface View {
        fun getContext(): AppCompatActivity
    }
}



