package com.example.account_service.controller

import com.example.account_service.command.CreateAccountCommand
import com.example.account_service.command.CreditMoneyCommand
import com.example.account_service.command.DepositMoneyCommand
import com.example.account_service.service.AccountCommandService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/accounts")
class AccountCommandController (
    private val commandService: AccountCommandService
){
    @PostMapping
    fun createAccount(@RequestBody command: CreateAccountCommand): ResponseEntity<String> {
        commandService.handle(command)
        return ResponseEntity.status(HttpStatus.CREATED).body("Account created with ID: ${command.accountId}")
    }

    @PostMapping("/{accountId}/deposit")
    fun depositMoney(
        @PathVariable accountId: UUID,
        @RequestBody request: AmountRequest
    ): ResponseEntity<String> {
        commandService.handle(DepositMoneyCommand(accountId, request.amount))
        return ResponseEntity.ok("Deposited ${request.amount} to account $accountId")
    }

    @PostMapping("/{accountId}/credit")
    fun creditMoney(
        @PathVariable accountId: UUID,
        @RequestBody request: AmountRequest
    ): ResponseEntity<String> {
        commandService.handle(CreditMoneyCommand(accountId, request.amount))
        return ResponseEntity.ok("Credited ${request.amount} from account $accountId")
    }

    data class AmountRequest(val amount: java.math.BigDecimal)
}