package com.s63d.erinvoiceservice.domain.db

import javax.persistence.*

@Entity
data class InvoiceLine (@Id @GeneratedValue val id: Long = 0, val tripId: Long, val length: Double, val price: Double, @OneToMany(cascade = [CascadeType.ALL]) val invoiceLinePart: List<InvoiceLinePart>)