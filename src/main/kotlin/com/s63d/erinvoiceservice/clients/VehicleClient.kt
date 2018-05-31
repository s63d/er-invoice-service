package com.s63d.erinvoiceservice.clients

import com.s63d.erinvoiceservice.domain.Ownership
import com.s63d.erinvoiceservice.domain.SimpleVehicle
import feign.Headers
import feign.Param
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*
import javax.ws.rs.PathParam

@FeignClient("vehicles", url = "\${urls.vehicle-service}/api")
 @RequestMapping("/vehicles")
 interface VehicleClient {

     @GetMapping("{license}")
     fun getById(@PathVariable license: String) : SimpleVehicle?

     @GetMapping("/current")
     fun getCurrentVehicles(@RequestParam user: Long, @RequestHeader("Authorization") token: String) : List<Ownership>
 }