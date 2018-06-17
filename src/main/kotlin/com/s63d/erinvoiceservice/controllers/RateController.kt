package com.s63d.erinvoiceservice.controllers

import com.s63d.erinvoiceservice.services.InvoiceService
import com.s63d.erinvoiceservice.services.RateService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/rates")
class RateController(private val rateService: RateService) {

    @PostMapping
    fun createRate(@RequestParam category: String, @RequestParam price: Double) = rateService.createRate(category, price)

    @PostMapping("{category}")
    fun updateRate(@PathVariable("category") category: String, @RequestParam price: Double?) = rateService.updateRate(category, price)

    @GetMapping
    fun getRate() = rateService.getRate()
}