package com.example.nami.presenters

import android.util.Log
import com.example.nami.controllers.services.ServiceInteractor
import com.example.nami.models.auth.sections.SectionFragment


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
                Log.i("Respuesta de secciones",data.toString())
                ui.showData(data)
            },
            { error ->
                ui.showError(error)
            })
    }
}