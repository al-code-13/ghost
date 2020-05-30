package com.example.nami.presenters

import com.example.nami.controllers.services.ServiceInteractor
import com.example.nami.models.detailModels.DetailResponse

interface DetailUI{
    fun showDetailInfo(data: DetailResponse)
    fun showError(error:String)
    fun orderModifiedSuccessfull(state:String)
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
    fun actionTake(token:String,orderId: Long,dateTake:String){
        interactor.postTakeOrder(token,orderId,dateTake, { data ->
            ui.orderModifiedSuccessfull("28")
        }, { error ->
            ui.showError(error)
        })
    }
    fun actionRelease(token:String,orderId: Long,observations:String){
        interactor.putReleaseOrder(token,orderId,observations, { data ->
            ui.orderModifiedSuccessfull("9")
        }, { error ->
            ui.showError(error)
        })
    }
    fun actionPutDeliverCourier(token:String,orderId: Long){
        interactor.putDeliverCourier(token,orderId, { data ->
            ui.orderModifiedSuccessfull("29")
        }, { error ->
            ui.showError(error)
        })
    }

    fun actionPutDeliverCustomer(token:String,orderId: Long){
        interactor.putDeliverCustomer(token,orderId, { data ->
            ui.orderModifiedSuccessfull("25")
        }, { error ->
            ui.showError(error)
        })
    }

    fun actionPutFreeze(token:String,orderId: Long,idReason:Int){
        interactor.putFreeze(token,orderId, idReason ,{ data ->
            ui.orderModifiedSuccessfull("25")
        }, { error ->
            ui.showError(error)
        })
    }
}