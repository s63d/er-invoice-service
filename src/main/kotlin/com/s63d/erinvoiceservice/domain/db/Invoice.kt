package com.s63d.erinvoiceservice.domain.db

import com.s63d.erinvoiceservice.domain.rest.InvoiceStatus
import java.util.*
import javax.persistence.*

@Entity
data class Invoice(val userId: Long, val date: Date = Date(), @Enumerated(EnumType.STRING) val status: InvoiceStatus, @OneToOne val rate: Rate, @OneToMany(cascade = arrayOf(CascadeType.ALL)) val invoiceLines: List<InvoiceLine>, val price: Double, @Id @GeneratedValue val id: Long = 0) {
}