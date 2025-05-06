package com.example.account_service.service


import com.example.account_service.command.CreateAccountCommand
import com.example.account_service.command.CreditMoneyCommand
import com.example.account_service.command.DepositMoneyCommand
import com.example.account_service.model.Account
import com.example.account_service.model.AccountEvent
import com.example.account_service.model.AccountEventType
import com.example.account_service.repository.AccountRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Service

@Service
class AccountProjector (
    private val accountRepository: AccountRepository,
    private val objectMapper: ObjectMapper = jacksonObjectMapper()
) {
    fun project(event: AccountEvent) {
        when (event.eventType) {
            AccountEventType.ACCOUNT_CREATED -> handleAccountCreated(event)
            AccountEventType.BALANCE_CREDITED -> handleMoneyCredited(event)
            AccountEventType.BALANCE_DEBITED -> handleMoneyDeposited(event)
        }
    }

    private fun handleAccountCreated(event: AccountEvent) {
        val payload = objectMapper.treeToValue(event.payload, CreateAccountCommand::class.java)
        val account = Account(
            id = event.accountId,
            owner = payload.ownerName,
            email = payload.email,
            balance = payload.initialBalance,
            createdAt = event.timestamp,
            updatedAt = event.timestamp
        )
        accountRepository.save(account)
    }

    private fun handleMoneyDeposited(event: AccountEvent) {
        val payload = objectMapper.treeToValue(event.payload, DepositMoneyCommand::class.java)
        val account = accountRepository.findById(event.accountId).orElseThrow()
        account.balance += payload.amount
        account.updatedAt = event.timestamp
        accountRepository.save(account)
    }

    private fun handleMoneyCredited(event: AccountEvent) {
        val payload = objectMapper.treeToValue(event.payload, CreditMoneyCommand::class.java)
        val account = accountRepository.findById(event.accountId).orElseThrow()
        account.balance -= payload.amount
        account.updatedAt = event.timestamp
        accountRepository.save(account)
    }
}