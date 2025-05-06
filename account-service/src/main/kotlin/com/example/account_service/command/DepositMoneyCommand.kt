package com.example.account_service.command

import java.math.BigDecimal
import java.util.UUID

data class DepositMoneyCommand(
    val accountId: UUID,
    val amount: BigDecimal
)