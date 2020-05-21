package com.example.nami.presenters

import android.util.Log
import com.example.nami.controllers.services.ServiceInteractor

interface LoginUI{
    fun showHome(token:String)
    fun showError(error:String)
}

class LoginPresenter (val ui: LoginUI){
    val interactor = ServiceInteractor()
    fun actionLogin(user: String, password: String) {
        interactor.postLogin(user, password, { data ->
            Log.i("TOKEN",data.token)
            ui.showHome(data.token)
        }, { error ->
            ui.showError(error)
        })
    }
}