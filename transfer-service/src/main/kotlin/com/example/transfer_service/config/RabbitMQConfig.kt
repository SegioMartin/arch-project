package com.example.transfer_service.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter

@Configuration
class RabbitMQConfig {

    @Bean
    fun jackson2MessageConverter(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }

    @Bean
    fun transferExchange(): TopicExchange {
        return TopicExchange("transfer.exchange")
    }

    @Bean
    fun debitQueue(): Queue {
        return Queue("debit-account-queue")
    }

    @Bean
    fun creditQueue(): Queue {
        return Queue("credit-account-queue")
    }

    @Bean
    fun debitBinding(
        transferExchange: TopicExchange,
        debitQueue: Queue
    ): Binding {
        return BindingBuilder.bind(debitQueue)
            .to(transferExchange)
            .with("transfer.debit")
    }

    @Bean
    fun creditBinding(
        transferExchange: TopicExchange,
        creditQueue: Queue
    ): Binding {
        return BindingBuilder.bind(creditQueue)
            .to(transferExchange)
            .with("transfer.credit")
    }
}