package com.s63d.erinvoiceservice.repositories

import com.s63d.erinvoiceservice.domain.db.Invoice
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository

interface InvoiceRepository : PagingAndSortingRepository<Invoice, Long> {

    @Query("SELECT i FROM Invoice i WHERE i.user.id = ?1")
    fun findByUserId(userId: Long) : List<Invoice>
}