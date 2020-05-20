package com.example.nami.presenters

import com.example.nami.controllers.services.ServiceInteractor

interface LoginUI{
    fun showHome()
    fun showError(error:String)
}

class LoginPresenter (val ui: LoginUI){
    val interactor = ServiceInteractor()
    fun actionLogin(user: String, password: String) {
        interactor.postLogin(user, password, { data ->
            print(data.token)
            ui.showHome()
        }, { error ->
            ui.showError(error)
        })
    }
}