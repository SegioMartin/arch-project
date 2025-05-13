package com.example.account_service.service

import com.example.account_service.command.CreateAccountCommand
import com.example.account_service.command.CreditMoneyCommand
import com.example.account_service.command.DepositMoneyCommand
import com.example.account_service.model.Account
import com.example.account_service.model.AccountEvent
import com.example.account_service.model.AccountEventType
import com.example.account_service.repository.AccountEventRepository
import com.example.account_service.repository.AccountRepository
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class AccountCommandService (
    private val accountEventRepository: AccountEventRepository,
    private val projectorService: AccountProjector,
    private val accountRepository: AccountRepository
) {
    private val objectMapper = jacksonObjectMapper()

    fun handle(command: CreateAccountCommand): Account {
        val payloadJson = objectMapper.valueToTree<JsonNode>(command)
        val event = AccountEvent(
            id = UUID.randomUUID(),
            accountId = UUID.randomUUID(),
            eventType = AccountEventType.ACCOUNT_CREATED,
            payload = payloadJson,
            timestamp = LocalDateTime.now()
        )
        accountEventRepository.save(event)
        return projectorService.project(event)
    }

    @Caching(
        evict = [
            CacheEvict(value = ["accounts"], key = "#command.accountId"),
            CacheEvict(value = ["accountsByEmail"], key = "#root.target.getEmailByAccountId(#command.accountId)")
        ]
    )
    fun handle(command: DepositMoneyCommand) {
        val payloadJson = objectMapper.valueToTree<JsonNode>(command)
        val event = AccountEvent(
            id = UUID.randomUUID(),
            accountId = command.accountId,
            eventType = AccountEventType.BALANCE_DEBITED,
            payload = payloadJson,
            timestamp = LocalDateTime.now()
        )
        accountEventRepository.save(event)
        projectorService.project(event)
    }

    @Caching(
        evict = [
            CacheEvict(value = ["accounts"], key = "#command.accountId"),
            CacheEvict(value = ["accountsByEmail"], key = "#root.target.getEmailByAccountId(#command.accountId)")
        ]
    )
    fun handle(command: CreditMoneyCommand) {
        val payloadJson = objectMapper.valueToTree<JsonNode>(command)
        val event = AccountEvent(
            id = UUID.randomUUID(),
            accountId = command.accountId,
            eventType = AccountEventType.BALANCE_CREDITED,
            payload = payloadJson,
            timestamp = LocalDateTime.now()
        )
        accountEventRepository.save(event)
        projectorService.project(event)
    }

    fun getEmailByAccountId(accountId: UUID): String {
        return accountRepository.findById(accountId)
            .orElseThrow { RuntimeException("Account not found") }
            .email
    }
}