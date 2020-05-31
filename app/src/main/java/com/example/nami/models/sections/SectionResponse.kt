import java.io.Serializable

data class SectionFragment(
    val orders: List<OrdersList>,
    val message: String?
)


data class OrdersList(
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

data class DetailOrder(
    val totalItems: Long
)

data class MethodPay(
    val id: Long,
    val name: String
)

data class PickingOrder(
    val list: List<Any?>
)

class OrdersListSerializable : Serializable {
    var id: Long? = null
    var name: String? = null
    var lastname: String? = null
    var address: String? = null
    var value: String? = null
    var phoneClient: String? = null
    var date: String? = null
    var origin: String? = null
    var idCodBranch: Long? = null
    var hour: String? = null
    var idState: Long? = null
    var observations: String? = null
    var methodPay: MethodPay? = null
    var pickingOrder: PickingOrder? = null
    var detailOrder: DetailOrder? = null
    var function: Long? = null

    constructor(
        id: Long,
        name: String,
        lastname: String,
        address: String,
        value: String,
        phoneClient: String,
        date: String,
        origin: String,
        idCodBranch: Long,
        hour: String,
        idState: Long,
        observations: String,
        methodPay: MethodPay,
        pickingOrder: PickingOrder,
        detailOrder: DetailOrder,
        function: Long
    ) {
        this.id = id
        this.name = name
        this.lastname = lastname
        this.address = address
        this.value = value
        this.phoneClient = phoneClient
        this.date = date
        this.origin = origin
        this.idCodBranch = idCodBranch
        this.hour = hour
        this.idState = idState
        this.observations = observations
        this.methodPay = methodPay
        this.pickingOrder = pickingOrder
        this.detailOrder = detailOrder
        this.function = function
    }
}