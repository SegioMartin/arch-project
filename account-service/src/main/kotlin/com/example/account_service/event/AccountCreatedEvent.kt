package com.example.account_service.event

import java.math.BigDecimal

data class AccountCreatedEvent (
    val accountId: String,
    val ownerName: String,
    val initialBalance: BigDecimal,
    override val timestamp: Long = System.currentTimeMillis()
) : DomainEvent