package com.example.account_service.controller

import com.example.account_service.model.Account
import com.example.account_service.service.AccountQueryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/accounts")
class AccountQueryController(
    private val queryService: AccountQueryService
) {

    @GetMapping
    fun getAllAccounts(): List<Account> {
        return queryService.getAll()
    }

    @GetMapping("/{id}")
    fun getAccountById(@PathVariable id: UUID): ResponseEntity<Account> {
        return try {
            ResponseEntity.ok(queryService.getById(id))
        } catch (ex: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/by-email/{email}")
    fun getAccountByEmail(@PathVariable email: String): ResponseEntity<Account> {
        return try {
            ResponseEntity.ok(queryService.getByEmail(email))
        } catch (ex: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }
}