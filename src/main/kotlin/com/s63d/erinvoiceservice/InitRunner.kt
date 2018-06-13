package com.s63d.erinvoiceservice

import com.s63d.erinvoiceservice.services.RateService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class InitRunner(private val rateService: RateService) : CommandLineRunner {
    override fun run(vararg args: String) {
        rateService.createRate("A", 0.001)
        rateService.createRate("B", 0.025)
        rateService.createRate("C", 0.05)
        rateService.createRate("D", 0.1)
        rateService.createRate("E", 0.2)
        rateService.createRate("F", 0.4)
    }
}