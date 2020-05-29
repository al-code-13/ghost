package com.example.nami.presenters


import android.util.Log
import com.example.nami.controllers.services.ServiceInteractor
import com.example.nami.models.detailModels.DetailResponse

interface DetailUI{
    fun showDetailInfo(data: DetailResponse)
    fun showError(error:String)
}

class DetailPresenter (private val ui: DetailUI){
    private val interactor = ServiceInteractor()
    fun actionDetail(token: String, orderId: Long) {
        interactor.getDetail(token, orderId, { data ->
            ui.showDetailInfo(data)
        }, { error ->
            ui.showError(error)
        })
    }
}