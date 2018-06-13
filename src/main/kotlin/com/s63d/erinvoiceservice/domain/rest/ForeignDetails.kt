package com.s63d.erinvoiceservice.domain.rest

data class ForeignDetails(val rate: Double, val description: String, val start: Long, val end: Long) {
    private constructor() : this(0.00, "", 0, 0)
}