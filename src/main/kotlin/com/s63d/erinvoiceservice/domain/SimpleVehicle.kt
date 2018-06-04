package com.s63d.erinvoiceservice.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class SimpleVehicle (val rate: String, val ownerId: Long, @OneToOne val carTracker: Cartracker, @Id val id: String)