package com.s63d.erinvoiceservice.clients

import com.s63d.erinvoiceservice.domain.Trip
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@FeignClient("trips", url = "\${urls.trip-service}/api")
@RequestMapping("/trips")
interface TripClient {
    @GetMapping("{license}")
    fun getById(@PathVariable license: String) : List<Trip>?
}