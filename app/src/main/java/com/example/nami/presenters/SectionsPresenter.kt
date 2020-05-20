package com.example.nami.presenters

import android.util.Log
import com.example.nami.controllers.services.ServiceInteractor
import com.example.nami.models.auth.sections.SectionsResponse

interface SectionsUI {
    fun showSection(data:SectionsResponse)
    fun showError(error: String)
}

class SectionsPresenter(val ui: SectionsUI) {

    val interactor = ServiceInteractor()
    fun actionSections(token: String) {
        interactor.getSections(
            token,
            { data ->
                Log.i("Respuesta de secciones",data.toString())
                ui.showSection(data)
            },
            { error ->
                ui.showError(error)
            })
    }
}