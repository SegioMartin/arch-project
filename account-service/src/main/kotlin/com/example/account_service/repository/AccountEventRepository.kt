package com.example.account_service.repository

import com.example.account_service.model.AccountEvent
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AccountEventRepository : JpaRepository<AccountEvent, UUID>