package com.example.nami.presenters

import SectionFragment
import android.util.Log
import com.example.nami.controllers.services.ServiceInteractor


interface SectionUI {
    fun showData(data: SectionFragment)
    fun showError(error: String)
}

class SectionPresenter(val ui: SectionUI) {

    val interactor = ServiceInteractor()
    fun actionSection(token: String,idSection:Long) {
        interactor.getSection(
            token,
            idSection,
            { data ->
                Log.i("Seccion individual",data.toString())
                ui.showData(data)
            },
            { error ->
                ui.showError(error)
            })
    }
}