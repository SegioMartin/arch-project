package com.example.transfer_service.messaging.publisher

import com.example.transfer_service.messaging.event.AbstractEvent
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class TransferEventPublisher(
    private val rabbitTemplate: RabbitTemplate
) {
    fun publish(event: AbstractEvent, routingKey: String) {
        rabbitTemplate.convertAndSend("transfer.exchange", "transfer.$routingKey", event)
    }
}