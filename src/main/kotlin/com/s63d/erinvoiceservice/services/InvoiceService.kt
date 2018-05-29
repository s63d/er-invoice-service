package com.s63d.erinvoiceservice.services

import com.s63d.erinvoiceservice.clients.TripClient
import com.s63d.erinvoiceservice.clients.VehicleClient
import com.s63d.erinvoiceservice.domain.Invoice
import com.s63d.erinvoiceservice.domain.InvoiceStatus
import com.s63d.erinvoiceservice.domain.Trip
import com.s63d.erinvoiceservice.repositories.InvoiceRepository
import com.s63d.erinvoiceservice.repositories.TripRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class InvoiceService(private val invoiceRepository: InvoiceRepository, private val tripRepository: TripRepository, private val tripClient: TripClient, private val vehicleClient : VehicleClient) {

    fun createInvoice(carId: String) : Invoice {
        val trips: List<Trip> = tripClient.getById(carId) ?: throw Exception("could not find trips")
        trips.forEach { tripRepository.save(it) }
        val rate = vehicleClient.getById(carId)?.rate ?: throw Exception("could not find car")
        val invoice = Invoice("piet", Date(), InvoiceStatus.OPEN, 350.00, trips, 3544.00, 1)
        return invoiceRepository.save(invoice)
    }

}