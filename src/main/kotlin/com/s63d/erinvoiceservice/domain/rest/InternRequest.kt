package com.s63d.erinvoiceservice.domain.rest

data class InternRequest(val id: String, val vehicleWeight: Int, val distance: Double, val country: String) {
    private constructor() : this("", 0, 0.0, "")
}