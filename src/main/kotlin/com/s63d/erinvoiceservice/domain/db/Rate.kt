package com.s63d.erinvoiceservice.domain.db

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Rate(@Id val category: String = "", var price: Double = 0.00)