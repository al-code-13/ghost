package com.example.nami.presenters

import android.util.Log
import com.example.nami.controllers.services.ServiceInteractor

interface LoginUI{
    fun showHome(token:String)
    fun showError(error:String)
    fun showLoad()
}

class LoginPresenter (private val ui: LoginUI){
    private val interactor = ServiceInteractor()
    fun actionLogin(user: String, password: String) {
        interactor.postLogin(user, password, { data ->
            ui.showLoad()
            ui.showHome(data.token)
        }, { error ->
            ui.showError(error)
        })
    }
}