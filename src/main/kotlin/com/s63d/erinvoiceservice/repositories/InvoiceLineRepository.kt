package com.s63d.erinvoiceservice.repositories

import com.s63d.erinvoiceservice.domain.db.InvoiceLine
import org.springframework.data.repository.CrudRepository

interface InvoiceLineRepository : CrudRepository<InvoiceLine, Long> {
}