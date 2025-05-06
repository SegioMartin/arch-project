package com.example.account_service.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "accounts")
data class Account(
    @Id //Primary key
    val id: UUID = UUID.randomUUID(),

    val owner: String,

    @Column(unique = true, nullable = false)
    val email: String,

    @Column(nullable = false, precision = 19, scale = 4)
    val balance: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()
)