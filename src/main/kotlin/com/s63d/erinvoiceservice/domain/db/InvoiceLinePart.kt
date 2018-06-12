package com.s63d.erinvoiceservice.domain.db

import javax.persistence.*

@Entity
data class InvoiceLinePart(@Id @GeneratedValue val id: Long = 0, val price: Double, val length: Double, val vat: Double, @OneToMany(cascade = [CascadeType.ALL]) val details: List<InvoiceLinePartDetail>)