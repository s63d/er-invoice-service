package com.s63d.erinvoiceservice.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class Ownership(val startDate:  String, val endDate: String?, @OneToOne val vehicle: SimpleVehicle, @Id val id: Long)