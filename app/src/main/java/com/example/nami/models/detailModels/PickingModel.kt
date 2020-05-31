package com.example.nami.models.detailModels

data class PickingOrderRequest (
    val listDataPicker: List<ListDataPicker>,
    val idOrder: Int,
    val idUser: Int,
    val productosok: Boolean,
    val totalPicker: String,
    val observations: String
)

data class ListDataPicker (
    val idDetailOrder: Int,
    val picker: String
)

data class PickingOrderResponse (
    val message: String
)