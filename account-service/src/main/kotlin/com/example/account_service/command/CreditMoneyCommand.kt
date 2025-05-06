package com.example.account_service.command

import java.math.BigDecimal
import java.util.UUID

data class CreditMoneyCommand(
    val accountId: UUID,
    val amount: BigDecimal
)