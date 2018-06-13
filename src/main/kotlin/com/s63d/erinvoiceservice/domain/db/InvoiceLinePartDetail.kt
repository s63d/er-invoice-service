package com.s63d.erinvoiceservice.domain.db

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class InvoiceLinePartDetail(@Id @GeneratedValue val id: Long = 0, val rate: Double, val description: String)