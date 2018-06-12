package com.s63d.erinvoiceservice.consumer

import com.s63d.erinvoiceservice.domain.rest.InternRequest
import com.s63d.erinvoiceservice.domain.rest.InternResponse
import com.s63d.erinvoiceservice.repositories.RateRepository
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.Exchange
import org.springframework.amqp.rabbit.annotation.Queue
import org.springframework.amqp.rabbit.annotation.QueueBinding
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

@Component
class ForeignConsumer (private val rabbitTemplate: RabbitTemplate, private val rateRepository: RateRepository) {
    val logger = LoggerFactory.getLogger(this::class.java!!)

    @RabbitListener(bindings = [(QueueBinding(value = Queue("INTERN_AT_REQ"), exchange = Exchange("AT"), key = ["req"]))])
    fun handle(internRequest: InternRequest) {
        logger.info("Receiving message from inside: " + internRequest)
        val rate = rateRepository.findById(getRateForWeight(internRequest.vehicleWeight).toString()).get()
        logger.info("Rate: " + rate)
        val ratePrice = rate.price
        var price = 0.00
        price += internRequest.distance / 1000 * ratePrice
        rabbitTemplate.convertAndSend("AT", "resp", InternResponse(internRequest.id, internRequest.distance, price, ratePrice, rate.category, internRequest.country))
    }

    private fun getRateForWeight(weight: Int): Char {
        val offset = 'A'
        val cat = Math.min(5.0, Math.ceil((weight / 1000).toDouble())).toInt()
        return (offset.toInt() + cat).toChar()
    }
}