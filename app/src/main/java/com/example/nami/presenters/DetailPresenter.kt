package com.example.nami.presenters

import com.example.nami.controllers.services.ServiceInteractor
import com.example.nami.models.detailModels.DetailResponse

interface DetailUI{
    fun showDetailInfo(data: DetailResponse)
    fun showError(error:String)
    fun orderModifiedSuccessfull()
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
    fun actionTake(token:String,orderId: Long,idUser:Long,dateTake:String){
        interactor.postTakeOrder(orderId,idUser,dateTake, { data ->
            ui.orderModifiedSuccessfull()
        }, { error ->
            ui.showError(error)
        })
    }
    fun actionRelease(token:String,orderId: Long,observations:String){
        interactor.putReleaseOrder(token,orderId,observations, { data ->
            ui.orderModifiedSuccessfull()
        }, { error ->
            ui.showError(error)
        })
    }
    fun actionPutDeliverCourier(token:String,orderId: Long){
        interactor.putDeliverCourier(token,orderId, { data ->
            ui.orderModifiedSuccessfull()
        }, { error ->
            ui.showError(error)
        })
    }

    fun actionPutDeliverCustomer(token:String,orderId: Long){
        interactor.putDeliverCustomer(token,orderId, { data ->
            ui.orderModifiedSuccessfull()
        }, { error ->
            ui.showError(error)
        })
    }

    fun actionPutFreeze(token:String,orderId: Long,idReason:Int){
        interactor.putFreeze(token,orderId, idReason ,{ data ->
            ui.orderModifiedSuccessfull()
        }, { error ->
            ui.showError(error)
        })
    }
}