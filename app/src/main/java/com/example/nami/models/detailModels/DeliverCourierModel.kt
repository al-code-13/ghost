package com.example.nami.models.detailModels

data class DeliverCourierRequest (
    val idOrder: Long,
    val idUser: Long
)
data class DeliverCourierResponse (
    val message: String
)