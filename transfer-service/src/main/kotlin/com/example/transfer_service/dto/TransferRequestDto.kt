package com.example.transfer_service.dto

import java.math.BigDecimal
import java.util.UUID

data class TransferRequestDto(
    val sourceAccountId: UUID,
    val destinationAccountId: UUID,
    val amount: BigDecimal
)