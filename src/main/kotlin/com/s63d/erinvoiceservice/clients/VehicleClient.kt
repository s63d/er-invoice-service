package com.s63d.erinvoiceservice.clients

import com.s63d.erinvoiceservice.domain.rest.SimplePage
import com.s63d.erinvoiceservice.domain.rest.SimpleVehicle
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*


@FeignClient("vehicles", url = "\${urls.vehicle-service}/api")
 @RequestMapping("/gov/vehicles")
 interface VehicleClient {

//     @GetMapping("{license}")
//     fun getById(@PathVariable license: String) : SimpleVehicle?

     @GetMapping()
     fun getCurrentVehicles(@RequestHeader("Authorization") token: String) : SimplePage<SimpleVehicle>
 }