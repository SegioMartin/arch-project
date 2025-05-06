package com.example.account_service.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "accounts")
data class Account(
    @Id //Primary key
    val id: UUID = UUID.randomUUID(),

    val owner: String,

    val balance: BigDecimal = BigDecimal.ZERO
)