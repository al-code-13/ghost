package com.example.nami.models.detailModels

data class DeliverConsumerRequest (
    val idOrder: Long,
    val idUser: Long
)
data class DeliverConsumerResponse (
    val message: String
)