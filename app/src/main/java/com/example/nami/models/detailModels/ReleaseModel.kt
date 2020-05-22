package com.example.nami.models.detailModels


data class ReleaseOrderRequest (
    val idOrder: Long,
    val idUser: Long,
    val observation: String
)
data class ReleaseOrderResponse (
    val message: String
)