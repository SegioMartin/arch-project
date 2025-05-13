package com.example.api_gateway.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import java.util.UUID

@RestController
@RequestMapping("/api/accounts")
class AccountController(
    private val restTemplate: RestTemplate,
    @Value("\${account-service.url}") private val accountServiceUrl: String
) {

    @PostMapping
    fun createAccount(@RequestBody request: Any): ResponseEntity<String?> {
        val url = "$accountServiceUrl/api/accounts"
        return restTemplate.postForEntity(url, request, String::class.java)
    }

    @PostMapping("/{id}/deposit")
    fun deposit(@PathVariable id: UUID, @RequestBody request: Any): ResponseEntity<String?> {
        val url = "$accountServiceUrl/api/accounts/$id/deposit"
        return restTemplate.postForEntity(url, request, String::class.java)
    }

    @PostMapping("/{id}/credit")
    fun credit(@PathVariable id: UUID, @RequestBody request: Any): ResponseEntity<String?> {
        val url = "$accountServiceUrl/api/accounts/$id/credit"
        return restTemplate.postForEntity(url, request, String::class.java)
    }

    @GetMapping
    fun getAllAccounts(): ResponseEntity<Any> {
        val url = "$accountServiceUrl/accounts"
        return restTemplate.getForEntity(url, Any::class.java)
    }

    @GetMapping("/{id}")
    fun getAccountById(@PathVariable id: UUID): ResponseEntity<Any> {
        val url = "$accountServiceUrl/accounts/$id"
        return restTemplate.getForEntity(url, Any::class.java)
    }

    @GetMapping("/by-email/{email}")
    fun getAccountByEmail(@PathVariable email: String): ResponseEntity<Any> {
        val url = "$accountServiceUrl/accounts/by-email/$email"
        return restTemplate.getForEntity(url, Any::class.java)
    }
}