package simple.reactive.api

import java.math.BigDecimal
import java.util.*

data class CustomerAccount(val customerId: Long,
                           val type: String,
                           val initialFunds: BigDecimal) {
    val id: UUID = UUID.randomUUID()
}