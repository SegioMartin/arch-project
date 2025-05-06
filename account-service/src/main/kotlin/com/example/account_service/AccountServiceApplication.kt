package com.example.account_service

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
@EnableRabbit
class AccountServiceApplication

fun main(args: Array<String>) {
	runApplication<AccountServiceApplication>(*args)
}
