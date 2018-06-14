package com.s63d.erinvoiceservice.controllers

import com.s63d.erinvoiceservice.services.InvoiceService
import com.s63d.erinvoiceservice.services.RateService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/rates")
class RateController(private val rateService: RateService) {

    @PostMapping
    fun createRate(@RequestParam category: String, @RequestParam price: Double) = rateService.createRate(category, price)

    @PutMapping
    fun updateRate(@RequestParam category: String, @RequestParam price: Double?) = rateService.updateRate(category, price)

    @GetMapping
    fun getRate() = rateService.getRate()
}