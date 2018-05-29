package com.s63d.erinvoiceservice.domain

import java.util.*
import javax.persistence.*

@Entity
data class Invoice(val client: String, val date: Date = Date(), val status: InvoiceStatus, val price: Double, @OneToMany val trips: List<Trip>, val length: Double, @Id val id: Long)