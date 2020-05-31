package com.example.nami.presenters

import SectionResponse
import android.content.Context
import android.util.Log
import com.example.nami.controllers.services.ServiceInteractor


interface SectionUI {
    fun showData(data: SectionResponse)
    fun showError(error: String)
}

class SectionPresenter(val ui: SectionUI) {

    val interactor = ServiceInteractor()
    fun actionSection(idSection:Long) {
        interactor.getSection(

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