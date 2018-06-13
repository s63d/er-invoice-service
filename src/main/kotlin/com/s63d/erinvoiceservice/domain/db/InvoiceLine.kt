package com.s63d.erinvoiceservice.domain.db

import javax.persistence.*

@Entity
data class InvoiceLine (@Id @GeneratedValue val id: Long = 0, val tripId: Long, val length: Int, val price: Double, @OneToMany(cascade = [CascadeType.ALL]) val invoiceLineParts: List<InvoiceLinePart>)