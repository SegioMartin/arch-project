package com.example.api_gateway.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/api/transfers")
class TransferController(
    private val restTemplate: RestTemplate,
    @Value("\${transfer-service.url}") private val transferServiceUrl: String
) {

    @PostMapping
    fun createTransfer(@RequestBody request: Any): ResponseEntity<String?> {
        val url = "$transferServiceUrl/api/transfers"
        return restTemplate.postForEntity(url, request, String::class.java)
    }
}