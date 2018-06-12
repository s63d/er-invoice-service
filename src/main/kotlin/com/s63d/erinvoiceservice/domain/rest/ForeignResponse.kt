package com.s63d.erinvoiceservice.domain.rest

data class ForeignResponse(val id: String, val price: Double, val distance: Int, val vat:Int, val origin: String, val details: List<ForeignDetails>) {
    private constructor() : this("", 0.0, 0,0, "", emptyList())
}