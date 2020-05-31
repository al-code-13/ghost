package com.example.nami.presenters

import android.content.Context
import com.example.nami.controllers.services.ServiceInteractor
import com.example.nami.models.detailModels.DetailResponse

interface DetailUI{
    fun showDetailInfo(data: DetailResponse)
    fun showError(error:String)
    fun orderModifiedSuccessfull(state:String)
}

class DetailPresenter (private val orderId:Int,private val ui: DetailUI){
    private val interactor = ServiceInteractor()
    fun actionDetail() {
        interactor.getDetail( orderId, { data ->
            ui.showDetailInfo(data)
        }, { error ->
            ui.showError(error)
        })
    }
    fun actionTake(dateTake:String){
        interactor.postTakeOrder(orderId,dateTake, { data ->
            ui.orderModifiedSuccessfull("28")
        }, { error ->
            ui.showError(error)
        })
    }
    fun actionRelease(observations:String){
        interactor.putReleaseOrder(orderId,observations, { data ->
            ui.orderModifiedSuccessfull("9")
        }, { error ->
            ui.showError(error)
        })
    }
    fun actionPutDeliverCourier(){
        interactor.putDeliverCourier(orderId, { data ->
            ui.orderModifiedSuccessfull("29")
        }, { error ->
            ui.showError(error)
        })
    }

    fun actionPutDeliverCustomer(){
        interactor.putDeliverCustomer(orderId, { data ->
            ui.orderModifiedSuccessfull("25")
        }, { error ->
            ui.showError(error)
        })
    }

    fun actionPutFreeze(idReason:Int){
        interactor.putFreeze(orderId, idReason ,{ data ->
            ui.orderModifiedSuccessfull("25")
        }, { error ->
            ui.showError(error)
        })
    }
}