package com.example.account_service.model

import com.fasterxml.jackson.databind.JsonNode
import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
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

    @JdbcTypeCode(SqlTypes.JSON)
    val payload: JsonNode,

    @Column(nullable = false)
    val timestamp: LocalDateTime = LocalDateTime.now()
)