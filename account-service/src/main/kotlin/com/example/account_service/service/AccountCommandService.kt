package com.example.account_service.service

import com.example.account_service.command.CreateAccountCommand
import com.example.account_service.command.CreditMoneyCommand
import com.example.account_service.command.DepositMoneyCommand
import com.example.account_service.model.AccountEvent
import com.example.account_service.model.AccountEventType
import com.example.account_service.repository.AccountEventRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class AccountCommandService (
    private val accountEventRepository: AccountEventRepository
) {
    fun handle(command: CreateAccountCommand) {
        val event = AccountEvent(
            id = UUID.randomUUID(),
            accountId = command.accountId,
            eventType = AccountEventType.ACCOUNT_CREATED,
            amount = command.initialBalance,
            createdAt = LocalDateTime.now()
        )
        accountEventRepository.save(event)
    }

    fun handle(command: DepositMoneyCommand) {
        val event = AccountEvent(
            id = UUID.randomUUID(),
            accountId = command.accountId,
            eventType = AccountEventType.BALANCE_DEBITED,
            amount = command.amount,
            createdAt = LocalDateTime.now()
        )
        accountEventRepository.save(event)
    }

    fun handle(command: CreditMoneyCommand) {
        val event = AccountEvent(
            id = UUID.randomUUID(),
            accountId = command.accountId,
            eventType = AccountEventType.BALANCE_CREDITED,
            amount = command.amount,
            createdAt = LocalDateTime.now()
        )
        accountEventRepository.save(event)
    }

}