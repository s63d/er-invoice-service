package com.s63d.erinvoiceservice.controllers

import com.s63d.erinvoiceservice.services.InvoiceService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/invoices")
class InvoiceController(private val invoiceService: InvoiceService) {

    @PostMapping
    fun getVehicles(@RequestHeader(HttpHeaders.AUTHORIZATION) authHeader: String) = invoiceService.getVehicles(authHeader)

//    @PostMapping
//    fun createInvoice(@RequestHeader(HttpHeaders.AUTHORIZATION) authHeader: String) = invoiceService.createInvoice(authHeader)

    @GetMapping("/parts")
    fun getByTripid(@RequestParam tripId: String) = invoiceService.getInvoiceLinePart(tripId)

    @GetMapping
    fun getByUserId(@RequestParam userId: Long) = invoiceService.getInvoice(userId)

    @GetMapping("/all")
    fun getAllInvoices(pageable: Pageable) = invoiceService.getAllInvoices(pageable)
}