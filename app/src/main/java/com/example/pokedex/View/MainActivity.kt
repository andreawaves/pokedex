package com.example.pokedex.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pokedex.Presenter.LoginPresenter
import com.example.pokedex.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LoginPresenter.View{

    var presenter : LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = LoginPresenter(this)

        btn_registro.setOnClickListener { irRegistro() }
        btn_login.setOnClickListener { login() }
    }


    private fun login() {
        val usuario = et_usuario.text.trim().toString()
        val password = et_password.text.trim().toString()
        presenter!!.login(usuario,password)
    }

    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    private fun irRegistro() {
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }

    override fun getContext():AppCompatActivity {
        return this
    }


}
