// package com.s63d.ertripservice.controllers

// import com.s63d.ertripservice.services.TripService
// import org.springframework.web.bind.annotation.*

// @RestController
// @RequestMapping("/api/trips")
// class TripController(private val tripService: TripService) {

//     @PostMapping
//     fun createNew(@RequestParam carTrackerId: String) = tripService.createNew(carTrackerId)

//     @GetMapping
//     fun getByCarTracker(@RequestParam trackerId: String) = tripService.byCarTracker(trackerId)

//     @GetMapping("{id}")
//     fun getByLicense(@PathVariable id: String) =
//             if(id.toLongOrNull() != null)
//                 tripService.byId(id.toLong())
//             else
//                 tripService.byCarId(id)
// }