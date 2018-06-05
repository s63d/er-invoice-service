package com.s63d.erinvoiceservice.services

import com.s63d.erinvoiceservice.domain.db.Rate
import com.s63d.erinvoiceservice.repositories.RateRepository
import org.springframework.stereotype.Service

@Service
class RateService(private val rateRepository: RateRepository) {
    fun createRate(category: String, price: Double)  : Rate = rateRepository.save(Rate(category, price))

    fun updateRate(category: String, price: Double?) : Rate {
        val rate = rateRepository.findById(category).get()
        rate.price = price ?: rate.price
        return rateRepository.save(rate)
    }
}