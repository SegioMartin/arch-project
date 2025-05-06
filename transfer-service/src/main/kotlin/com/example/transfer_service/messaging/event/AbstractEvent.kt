package com.example.transfer_service.messaging.event

import java.io.Serializable
import java.math.BigDecimal
import java.util.UUID

abstract class AbstractEvent: Serializable {
    abstract val accountId: UUID
    abstract val amount: BigDecimal
}