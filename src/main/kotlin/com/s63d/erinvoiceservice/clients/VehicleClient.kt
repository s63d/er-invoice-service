package com.s63d.erinvoiceservice.clients

//import com.s63d.ertripservice.domain.SimpleVehicle
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

// @FeignClient("vehicles", url = "\${urls.vehicle-service}/api")
// @RequestMapping("/vehicles")
// interface VehicleClient {

//     @GetMapping("{license}")
//     fun getById(@PathVariable license: String) : SimpleVehicle?
// }