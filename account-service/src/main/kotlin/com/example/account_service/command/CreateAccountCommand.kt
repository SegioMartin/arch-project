package com.example.account_service.command

import java.math.BigDecimal

data class CreateAccountCommand(
    val ownerName: String,
    val email: String,
    val initialBalance: BigDecimal
)