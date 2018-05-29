package com.s63d.erinvoiceservice.repositories

import com.s63d.erinvoiceservice.domain.Invoice
import org.springframework.data.repository.CrudRepository

interface InvoiceRepository : CrudRepository<Invoice, Long> {
    fun findByUserId(user: Long) : List<Invoice>
}