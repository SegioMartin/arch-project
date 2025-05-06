package com.example.transfer_service.messaging.event

import java.math.BigDecimal
import java.util.UUID

data class CreditAccountEvent(
    override val accountId: UUID,
    override val amount: BigDecimal
): AbstractEvent()