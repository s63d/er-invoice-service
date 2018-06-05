package com.s63d.erinvoiceservice.domain.rest

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

data class SimpleVehicle (val rate: String = "", val ownerId: Long = 0, val carTrackerId: String? = "", @Id val id: String = "")