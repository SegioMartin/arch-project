package com.example.account_service.messaging

import com.example.account_service.command.CreditMoneyCommand
import com.example.account_service.command.DepositMoneyCommand
import com.example.account_service.service.AccountCommandService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class TransferEventListener(
    private val commandService: AccountCommandService
) {

    @RabbitListener(queues = ["debit-account-queue"])
    fun handleDebit(message: DepositMoneyCommand) {
        println("Получено debit-сообщение: $message")
        commandService.handle(message)
    }

    @RabbitListener(queues = ["credit-account-queue"])
    fun handleCredit(message: CreditMoneyCommand) {
        println("Получено credit-сообщение: $message")
        commandService.handle(message)
    }
}