package com.s63d.erinvoiceservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter


@Configuration
class RabbitConfig {

    @Bean
    fun jsonRabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        val template = RabbitTemplate(connectionFactory)
        template.messageConverter = jsonConverter()
        return template
    }

    @Bean
    fun jsonConverter() = Jackson2JsonMessageConverter()
}