package com.s63d.erinvoiceservice.domain.db

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class InvoiceLine (@Id @GeneratedValue val id: Long = 0, val tripId: Long, val length: Double, @OneToOne val rate: Rate)