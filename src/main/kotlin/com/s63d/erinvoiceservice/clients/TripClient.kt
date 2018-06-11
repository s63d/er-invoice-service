package com.s63d.erinvoiceservice.clients

import com.s63d.erinvoiceservice.domain.rest.SimplePage
import com.s63d.erinvoiceservice.domain.rest.Trip
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*

@FeignClient("trips", url = "\${urls.trip-service}/api")
@RequestMapping("/trips")
interface TripClient {

    @GetMapping
    fun getById(@RequestHeader("Authorization") token: String, @RequestParam(value= "vehicleId", required = true) trackerId: String) : SimplePage<Trip>?
}