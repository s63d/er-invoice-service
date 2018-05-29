// package com.s63d.ertripservice.services

// import com.s63d.ertripservice.clients.VehicleClient
// import com.s63d.ertripservice.domain.CarTracker
// import com.s63d.ertripservice.domain.Trip
// import com.s63d.ertripservice.domain.TripResponse
// import com.s63d.ertripservice.repositories.TripRepository
// import org.springframework.stereotype.Service

// @Service
// class TripService(private val tripRepository: TripRepository, private val vehicleClient: VehicleClient) {

//     fun createNew(carTrackerId: String) : Trip {
//         val trip = Trip(CarTracker(carTrackerId))
//         return tripRepository.save(trip)
//     }

//     fun byCarTracker(trackerId: String) = tripRepository.findByCarTrackerId(trackerId).map { TripResponse(it) }

//     fun byId(id: Long) = TripResponse(tripRepository.findById(id).orElseThrow { Exception("Invalid trip id") }) // TODO response status

//     fun byCarId(id: String): List<TripResponse> {
//         val carTracker = vehicleClient.getById(id)?.carTracker ?: throw Exception("Vehicle $id not found") // TODO response status not found
//         return byCarTracker(carTracker.carTrackerId)
//     }
// }