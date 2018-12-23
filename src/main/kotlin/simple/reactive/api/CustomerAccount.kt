package simple.reactive.api

import java.math.BigDecimal
import java.util.*

data class CustomerAccount(val id: UUID,
                           val type: String,
                           val initialFunds: BigDecimal)
