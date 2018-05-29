package com.s63d.erinvoiceservice.controllers

import com.s63d.erinvoiceservice.services.InvoiceService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/invoices")
class InvoiceController(private val invoiceService: InvoiceService) {

    @PostMapping
    fun createInvoice() = invoiceService.createInvoice("cf03efdc396d771162d9c38858bcfadc")

    @GetMapping
    fun getByUserId(@RequestParam userId: Long) = invoiceService.getInvoice(id)
}