package com.example.account_service.repository

import com.example.account_service.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AccountRepository : JpaRepository<Account, UUID> {
    fun findByEmail(email: String): Account?
}