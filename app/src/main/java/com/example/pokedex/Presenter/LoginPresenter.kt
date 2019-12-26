package com.example.pokedex.Presenter

import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.Model.Entrenador
import io.realm.Realm
import io.realm.RealmConfiguration

class LoginPresenter (val view: LoginView){
    var realm : Realm
    var mContext : AppCompatActivity

    init {
        mContext = view.getContext()
        Realm.init(mContext)
        val config = RealmConfiguration.Builder().directory(mContext.getExternalFilesDir(null)!!)
            .name("entrenador.realm").build()
        realm = Realm.getInstance(config)
    }

    fun login(usuario:String, password:String){
        if (usuario==""||password==""){
            view.mostrarMensaje("Complete todos los campos")
        } else {
            var p = retornarPassword(usuario)
            if (p == null) {
                view.mostrarMensaje("¡No existes!")
            } else {
                view.mostrarMensaje("¡Benvenuto!")
            }
        }
    }

    fun retornarPassword(usuario:String) : String?  {
        val entrenadores = realm.where(Entrenador::class.java).equalTo("usuario",usuario).findAll()
        if (entrenadores.isEmpty()){
            return null
        }
        else {
            return entrenadores[0]!!.password
        }
    }
}

interface LoginView {
    fun mostrarMensaje(mensaje:String)
    fun getContext(): AppCompatActivity
}