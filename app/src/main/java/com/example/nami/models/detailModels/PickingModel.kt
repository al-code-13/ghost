package com.example.nami.models.detailModels

data class PickingOrderRequest (
    val listDataPicker: List<ListDataPicker>,
    val idOrder: Long,
    val idUser: Long,
    val productosok: Boolean,
    val totalPicker: String,
    val observations: String
)

data class ListDataPicker (
    val idDetailOrder: Long,
    val picker: String
)

data class PickingOrderResponse (
    val message: String
)