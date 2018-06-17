package com.s63d.erinvoiceservice.controllers

import com.s63d.erinvoiceservice.domain.rest.InvoiceSummary
import com.s63d.erinvoiceservice.services.InvoiceService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/invoices")
class InvoiceController(private val invoiceService: InvoiceService) {

    @PostMapping
    fun getVehicles(@RequestHeader(HttpHeaders.AUTHORIZATION) authHeader: String) = invoiceService.getVehicles(authHeader)

    @GetMapping
    fun getByUserId(principal: Principal) = invoiceService.getInvoices(principal.name.toLong()).map { InvoiceSummary(it) }

    @GetMapping("{id}")
    fun getInvoiceById(@PathVariable id: Long) = invoiceService.getInvoiceById(id)

    @GetMapping("/all")
    fun getAllInvoices(pageable: Pageable) = invoiceService.getAllInvoices(pageable)

    @PostMapping("paid")
    fun updateInvoicePaid(@RequestParam id: Long, @RequestParam paid: Boolean) = invoiceService.updateInvoicePaid(id, paid)
}