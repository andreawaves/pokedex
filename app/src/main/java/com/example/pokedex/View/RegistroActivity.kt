package com.example.pokedex.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pokedex.Presenter.RegistroPresenter
import com.example.pokedex.Presenter.View
import com.example.pokedex.R
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity(), View {

    private var presenter: RegistroPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        btn_guardar.setOnClickListener {registrar()}
        presenter = RegistroPresenter(this)

    }

    private fun registrar() {
        val iusuario = et_iusuario.text.trim().toString()
        val ipassword1 = et_ipassword1.text.trim().toString()
        val ipassword2 = et_ipassword2.text.trim().toString()

        presenter!!.registrarEntrenador(iusuario, ipassword1, ipassword2)
    }

    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    override fun getContext():AppCompatActivity {
        return this
    }
}
