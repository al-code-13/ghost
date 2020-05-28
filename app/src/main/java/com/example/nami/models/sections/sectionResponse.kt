
data class SectionFragment (
    val orders: List<OrdersList>,
    val message:String?
)


data class OrdersList (
    val id: Long,
    val name: String,
    val lastname: String,
    val address: String,
    val value: String,
    val phoneClient: String,
    val date: String,
    val origin: String,
    val idCodBranch: Long,
    val hour: String,
    val idState: Long,
    val observations: String,
    val methodPay: MethodPay,
    val pickingOrder: PickingOrder,
    val detailOrder: DetailOrder,
    val function: Long
)

data class DetailOrder (
    val totalItems: Long
)

data class MethodPay (
    val id: Long,
    val name: String
)

data class PickingOrder (
    val list: List<Any?>
)
