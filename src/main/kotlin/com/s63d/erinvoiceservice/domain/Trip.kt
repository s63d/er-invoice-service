package com.s63d.erinvoiceservice.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Column

@Entity
data class Trip(@Id val tripId: Long, val length: Long,@Column(columnDefinition = "TEXT") val polyline: String)