package com.example.nami.presenters

import com.example.nami.controllers.services.ServiceInteractor
import com.example.nami.models.detailModels.DetailResponse

interface DetailUI {
    fun showDetailInfo(data: DetailResponse)
    fun showError(error: String)
    fun showDetailFunctionReleased()
    fun showDetailFunctionTaked()
    fun showDetailFunctioDeliverCourier()
    fun showDetailFunctionDeliverCustomer()
    fun showDetailFunctionFreeze()
}

class DetailPresenter(private val orderId: Int, private val ui: DetailUI) {
    private val interactor = ServiceInteractor()
    fun actionDetail() {
        interactor.getDetail(orderId, { data ->
            ui.showDetailInfo(data)
        }, { error ->
            ui.showError(error)
        })
    }

    fun actionTake() {
        interactor.putTakeOrder(orderId, { data ->
            ui.showDetailFunctionTaked()
        }, { error ->
            ui.showError(error)
        })
    }

    fun actionRelease(observations: String) {
        interactor.putReleaseOrder(orderId, observations, { data ->
            ui.showDetailFunctionReleased()
        }, { error ->
            ui.showError(error)
        })
    }

    fun actionPutDeliverCourier() {
        interactor.putDeliverCourier(orderId, { data ->
            ui.showDetailFunctioDeliverCourier()
        }, { error ->
            ui.showError(error)
        })
    }

    fun actionPutDeliverCustomer() {
        interactor.putDeliverCustomer(orderId, { data ->
            ui.showDetailFunctionDeliverCustomer()
        }, { error ->
            ui.showError(error)
        })
    }

    fun actionPutFreeze(idReason: Int) {
        interactor.putFreeze(orderId, idReason, { data ->
            ui.showDetailFunctionFreeze()
        }, { error ->
            ui.showError(error)
        })
    }
}