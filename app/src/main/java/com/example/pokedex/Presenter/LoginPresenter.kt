package com.example.pokedex.Presenter

class LoginPresenter (val view: LoginView){
    fun login(usuario:String, password:String){
        if (usuario==""||password==""){
            view.mostrarMensaje("Complete todos los campos")
        } else {

        }
    }
}

interface LoginView {
    fun mostrarMensaje(mensaje:String)
}