package com.s63d.erinvoiceservice.services

import com.s63d.erinvoiceservice.clients.TripClient
import com.s63d.erinvoiceservice.clients.VehicleClient
import com.s63d.erinvoiceservice.domain.Invoice
import com.s63d.erinvoiceservice.domain.InvoiceStatus
import com.s63d.erinvoiceservice.domain.SimpleUser
import com.s63d.erinvoiceservice.domain.Trip
import com.s63d.erinvoiceservice.repositories.InvoiceRepository
import com.s63d.erinvoiceservice.repositories.RateRepository
import com.s63d.erinvoiceservice.repositories.TripRepository
import com.s63d.erinvoiceservice.repositories.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class InvoiceService(private val invoiceRepository: InvoiceRepository, private val tripRepository: TripRepository, private val rateRepository: RateRepository, private val userRepository: UserRepository, private val tripClient: TripClient, private val vehicleClient : VehicleClient) {

    fun createInvoice(carId: String) : Invoice {
        var length:Long = 0;
        val trips: List<Trip> = tripClient.getById(carId) ?: throw Exception("could not find trips")
        trips.forEach { tripRepository.save(it);length+=it.length }
        val rate = vehicleClient.getById(carId)?.rate ?: throw Exception("could not find car")
        val price = rateRepository.findById(rate).get().price

        val user = SimpleUser("Piet", "Janssen", "Rachelsmolen 1","5709ZZ","Eindhoven", 1)
        userRepository.save(user)
        val invoice = Invoice(user, Date(), InvoiceStatus.OPEN, 350.00, trips, length.toDouble(), 1)

        if(invoiceRepository.existsById(invoice.id))
            throw Exception("Invoice already registered")
        return invoiceRepository.save(invoice)
    }

    fun getInvoice(clientId: Long) : List<Invoice> {
        return invoiceRepository.findByClientId(clientId)
    }

}