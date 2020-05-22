package com.example.nami.models.detailModels

data class DataResponse (
    val order: Order,
    val message:String?
)

data class Order (
    val id: Long,
    val comments: String,
    val email: String,
    val turns: String,
    val deliveryValue: String,
    val service: String,
    val reasonNotDelivery: Any? = null,
    val detailOrder: DetailOrder
)

data class DetailOrder (
    val list: List<ListElement>
)

data class ListElement (
    val id: Long,
    val description: Any? = null,
    val quantityArticle: String,
    val valueTotalArticle: String,
    val codOptionalsExternals: Any? = null,
    val codTamano: Any? = null,
    val observations: Any? = null,
    val picking: Any? = null,
    val article: Article
)

data class Article (
    val id: Long,
    val name: String,
    val description: String,
    val value: String,
    val image: String,
    val codSofware: String
)