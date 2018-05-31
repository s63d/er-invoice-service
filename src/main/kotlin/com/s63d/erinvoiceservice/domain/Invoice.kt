package com.s63d.erinvoiceservice.domain

import java.util.*
import javax.persistence.*

@Entity
data class Invoice(@ManyToOne val user: SimpleUser, val date: Date = Date(),@Enumerated(EnumType.STRING) val status: InvoiceStatus, val price: String, @OneToMany val trips: List<Trip>, val length: Double, @Id @GeneratedValue val id: Long = 0) {
}