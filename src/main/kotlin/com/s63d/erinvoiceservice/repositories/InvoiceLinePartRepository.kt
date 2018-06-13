package com.s63d.erinvoiceservice.repositories

import com.s63d.erinvoiceservice.domain.db.InvoiceLinePart
import org.springframework.data.repository.CrudRepository

interface InvoiceLinePartRepository : CrudRepository<InvoiceLinePart, Long> {
    fun findByTripid(tripid: String) : List<InvoiceLinePart>
}