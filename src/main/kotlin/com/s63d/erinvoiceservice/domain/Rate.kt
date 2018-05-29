package com.s63d.erinvoiceservice.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Rate(@Id val category: String = "", var price: Double)