package com.s63d.erinvoiceservice.domain.db

import javax.persistence.*

@Entity
data class InvoiceLinePart(@Id @GeneratedValue val id: Long = 0, val tripid: String, val price: Double, val length: Int, val vat: Int, val origin: String, @OneToMany(cascade = [CascadeType.ALL]) val details: List<InvoiceLinePartDetail>)