package com.s63d.erinvoiceservice.domain

import javax.persistence.EnumType
import javax.persistence.Enumerated

enum class InvoiceStatus {
    OPEN, DUE, PAID
}