package com.s63d.erinvoiceservice.domain.rest

import javax.persistence.EnumType
import javax.persistence.Enumerated

enum class InvoiceStatus {
    OPEN, PAID
}