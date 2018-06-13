package com.s63d.erinvoiceservice.domain.rest

import com.s63d.erinvoiceservice.domain.db.Invoice
import java.util.*

data class InvoiceSummary(val id: Long, val vehicleId: String, val distance: Int, val price: Double, val date: Date, val status: String) {
    constructor(invoice: Invoice) : this(invoice.id, invoice.vehicleId, invoice.distance, invoice.price, invoice.date, invoice.status.toString())
}