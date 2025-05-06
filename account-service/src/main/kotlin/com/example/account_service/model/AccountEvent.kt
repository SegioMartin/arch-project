package com.example.account_service.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "account_events")
data class AccountEvent(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val accountId: UUID,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val eventType: AccountEventType,

    @Column
    val amount: BigDecimal? = null,

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)