package com.example.nami.presenters

import com.example.nami.controllers.services.ServiceInteractor

interface SectionsUI{
    fun showSection()
    fun showError(error:String)
}

class SectionsPresenter (val ui : SectionsUI){

    val interactor = ServiceInteractor()
    fun actionLogin(user: String, password: String) {
        interactor.postLogin(user, password, { data ->
            print(data.token)
            ui.showSection()
        }, { error ->
            ui.showError(error)
        })
    }
}