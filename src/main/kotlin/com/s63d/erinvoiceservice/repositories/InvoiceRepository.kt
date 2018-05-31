package com.s63d.erinvoiceservice.repositories

import com.s63d.erinvoiceservice.domain.Invoice
import org.springframework.data.repository.PagingAndSortingRepository

interface InvoiceRepository : PagingAndSortingRepository<Invoice, Long> {
    fun findByUserId(user: Long) : List<Invoice>
}