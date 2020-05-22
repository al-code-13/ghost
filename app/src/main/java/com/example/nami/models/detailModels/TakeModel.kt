package com.example.nami.models.detailModels

data class TakeOrderRequest (
    val idOrder: Long,
    val idUser: Long,
    val dateTake: String
)
data class TakeOrderResponse (
    val message: String,
    val idPickingxOrder: Long
)