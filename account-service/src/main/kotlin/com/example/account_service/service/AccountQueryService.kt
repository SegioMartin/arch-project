package com.example.account_service.service

import com.example.account_service.model.Account
import com.example.account_service.repository.AccountRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AccountQueryService(
    private val accountRepository: AccountRepository
) {
    @Cacheable(value = ["accounts"], key = "#id")
    fun getById(id: UUID): Account {
        println("Чтение из базы по ID") // будет видно, работает ли кэш
        return accountRepository.findById(id)
            .orElseThrow { RuntimeException("Account not found") }
    }

    @Cacheable(value = ["accountsByEmail"], key = "#email")
    fun getByEmail(email: String): Account {
        println("Чтение из базы по Email")
        return accountRepository.findByEmail(email)
            ?: throw RuntimeException("Account not found")
    }

    fun getAll(): List<Account> {
        // не будем кэшировать список, он часто меняется
        return accountRepository.findAll()
    }
}