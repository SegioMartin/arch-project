package com.example.account_service.repository

import com.example.account_service.model.Account
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.UUID

@ActiveProfiles("test")
@DataJpaTest
class AccountRepositoryTest @Autowired constructor(
    val accountRepository: AccountRepository
){

    @Test
    fun `should save and retrieve account`() {
        val account = Account(
            id = UUID.randomUUID(),
            owner = "John Doe",
            balance = BigDecimal("100.00")
        )

        accountRepository.save(account)

        val found = accountRepository.findById(account.id)

        assertTrue(found.isPresent)
        assertEquals("John Doe", found.get().owner)
        assertEquals(BigDecimal("100.00"), found.get().balance)
    }

    @Test
    fun `should delete account`() {
        val account = Account(
            id = UUID.randomUUID(),
            owner = "Jane Smith",
            balance = BigDecimal("50.00")
        )

        accountRepository.save(account)
        accountRepository.delete(account)

        val found = accountRepository.findById(account.id)
        assertFalse(found.isPresent)
    }
}