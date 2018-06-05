package com.s63d.erinvoiceservice.clients

import com.s63d.erinvoiceservice.domain.rest.Trip
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*

@RequestMapping("/trips")
@FeignClient("trips", url = "\${urls.trip-service}/api")
interface TripClient {

    @GetMapping
    fun getById(@RequestHeader("Authorization") token: String, @RequestParam(value= "trackerId", required = true) trackerId: String) : List<Trip>?
}