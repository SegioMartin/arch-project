package com.example.transfer_service.service

import com.example.transfer_service.dto.TransferRequestDto
import com.example.transfer_service.messaging.event.CreditAccountEvent
import com.example.transfer_service.messaging.event.DebitAccountEvent
import com.example.transfer_service.messaging.publisher.TransferEventPublisher
import org.springframework.stereotype.Service

@Service
class TransferService(
    private val eventPublisher: TransferEventPublisher
) {
    fun processTransfer(dto: TransferRequestDto) {
        val creditEvent = CreditAccountEvent(
            accountId = dto.sourceAccountId,
            amount = dto.amount
        )
        val debitEvent = DebitAccountEvent(
            accountId = dto.destinationAccountId,
            amount = dto.amount
        )
        eventPublisher.publish(creditEvent, "credit")
        eventPublisher.publish(debitEvent, "debit")
    }
}