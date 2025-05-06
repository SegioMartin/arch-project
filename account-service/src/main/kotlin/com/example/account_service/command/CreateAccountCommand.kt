package com.example.account_service.command

data class CreateAccountCommand(
    val accountId: String,
    val ownerName: String,
    val email: String,
    val initialBalance: Long
)