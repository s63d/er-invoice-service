package com.s63d.erinvoiceservice.domain.db

import javax.persistence.*

@Entity
data class InvoiceLinePart(@Id @GeneratedValue val id: Long = 0, val tripId: Long, val price: Double, val distance: Int, val vat: Int, val origin: String, @OneToMany(cascade = [CascadeType.ALL]) val details: List<InvoiceLinePartDetail>)