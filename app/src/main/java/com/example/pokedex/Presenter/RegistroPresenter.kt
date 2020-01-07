package com.example.pokedex.Presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.View.MainActivity
import com.example.pokedex.Model.Entrenador
import io.realm.Realm
import io.realm.RealmConfiguration

class RegistroPresenter (val view: RegistroPresenter.View) {

    var realm : Realm
    var mContext : AppCompatActivity

    init {
        Realm.init(view.getContext())
        val config = RealmConfiguration.Builder()
            .name("entrenador.realm").build()
        realm = Realm.getInstance (config)
        mContext = view.getContext()
    }

    fun registrarEntrenador(iusuario:String, ipassword1:String, ipassword2: String) {
        if (iusuario==""||ipassword1==""||ipassword2==""){
            view.mostrarMensaje("Complete todos los campos")
        } else {
            if (ipassword1!=ipassword2) {
                view.mostrarMensaje("Las contraseñas no coinciden")
            } else {
                guardarEntrenador(iusuario,ipassword1)
            }
        }
    }

    fun guardarEntrenador(usuario:String, password:String){
        if (!existe(usuario)){
            realm.beginTransaction()
            val entrenador = realm.createObject(Entrenador::class.java)
            entrenador.usuario = usuario
            entrenador.password = password
            realm.commitTransaction()
            view.mostrarMensaje("¡Usuario registrado!")
            volverMain()
        } else {
            view.mostrarMensaje("Escoge otro nombre de usuario")
        }

    }

    fun existe(usuario:String):Boolean{
        val entrenadores = realm.where(Entrenador::class.java).equalTo("usuario",usuario).findAll()
        if (entrenadores.isEmpty()) {
            return false
        }
        return true
    }

    fun volverMain() {
        val intent = Intent(mContext, MainActivity::class.java)
        mContext.startActivity(intent)
    }

    interface View {
        fun mostrarMensaje(mensaje : String)
        fun getContext():AppCompatActivity
    }

}


