package com.example.nami.models.auth.sections

data class SectionFragment(
    val orders: Orders,
    val message: String?
)

data class Orders(
    val list: List<OrdersList>
)

data class OrdersList(
    val id: Long,
    val name: String,
    val lastname: String,
    val address: String,
    val value: String,
    val phoneClient: String,
    val date: String,
    val origin: Origin,
    val idCodBranch: Long,
    val hour: String,
    val idState: Long,
    val observations: Observations,
    val methodPay: MethodPay,
    val pickingOrder: PickingOrder,
    val detailOrder: DetailOrder,
    val action: Long? = null
)

data class DetailOrder(
    val totalItems: Long
)

data class MethodPay(
    val id: Long,
    val name: Name
)

enum class Name {
    Datafono,
    Efectivo
}

enum class Observations {
    Ok
}

enum class Origin {
    Pf
}

data class PickingOrder(
    val list: List<PickingOrderList>
)

data class PickingOrderList(
    val id: Long,
    val idUser: Long,
    val totalPicker: String? = null,
    val observations: Any? = null
)