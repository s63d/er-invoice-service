package com.s63d.erinvoiceservice.services

import com.s63d.erinvoiceservice.clients.TripClient
import com.s63d.erinvoiceservice.clients.UserClient
import com.s63d.erinvoiceservice.clients.VehicleClient
import com.s63d.erinvoiceservice.domain.*
import com.s63d.erinvoiceservice.repositories.InvoiceRepository
import com.s63d.erinvoiceservice.repositories.RateRepository
import com.s63d.erinvoiceservice.repositories.TripRepository
import com.s63d.erinvoiceservice.repositories.UserRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*
import kotlin.math.round

@Service
class InvoiceService(private val invoiceRepository: InvoiceRepository, private val tripRepository: TripRepository, private val rateRepository: RateRepository, private val userRepository: UserRepository, private val tripClient: TripClient, private val vehicleClient : VehicleClient, private val userClient: UserClient) {

    fun createInvoice(authHeader:String) : List<Invoice> {
        val users = userClient.getAllUsers(authHeader)
        var invoices: List<Invoice> = listOf()
        users.forEach {
            invoices += generateInvoice(it,authHeader)
        }
        return invoices
    }
    //TODO REFACTOR
    private fun generateInvoice(user: SimpleUser, authHeader: String): Invoice {
        var price = 0.00
        var totalLength = 0
        var trips: List<Trip> = listOf()

        val ownerShips = vehicleClient.getCurrentVehicles(user.id, authHeader)
        ownerShips.forEach {
            val vehicleId = it.vehicle.id
            val allTripsFromVehicle = tripClient.getById(vehicleId) ?: throw Exception("could not find trips")
            allTripsFromVehicle.forEach {
                val rate = vehicleClient.getById(vehicleId)?.rate ?: throw Exception("could not find car")
                price += it.length * rateRepository.findById(rate).get().price
                totalLength += it.length.toInt()
                tripRepository.save(it)
            }
            trips += allTripsFromVehicle
        }

        val invoice = Invoice(user, Date(), InvoiceStatus.OPEN, "%.2f".format(price), trips, totalLength.toDouble())

        userRepository.save(user)
        if(invoiceRepository.existsById(invoice.id))
            throw Exception("Invoice already registered")
        return invoiceRepository.save(invoice)

    }

    fun getInvoice(userId: Long) : List<Invoice> {
        return invoiceRepository.findByUserId(userId)
    }

    fun getAllInvoices(pageable: Pageable) = invoiceRepository.findAll(pageable);

}