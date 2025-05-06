package com.example.transfer_service.controller

import com.example.transfer_service.dto.TransferRequestDto
import com.example.transfer_service.service.TransferService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/transfers")
class TransferController(
    private val transferService: TransferService
) {
    @PostMapping
    fun transfer(@RequestBody dto: TransferRequestDto): ResponseEntity<String> {
        println("helo1")
        transferService.processTransfer(dto)
        return ResponseEntity.ok("Message accepted")
    }
}