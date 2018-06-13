package com.s63d.erinvoiceservice.domain.db

import com.s63d.erinvoiceservice.domain.rest.InvoiceStatus
import java.util.*
import javax.persistence.*

@Entity
data class Invoice(@Embedded val user: User, val date: Date = Date(), @Enumerated(EnumType.STRING) val status: InvoiceStatus, @OneToMany(cascade = arrayOf(CascadeType.ALL)) val lines: List<InvoiceLine>, val price: Double, @Id @GeneratedValue val id: Long = 0)