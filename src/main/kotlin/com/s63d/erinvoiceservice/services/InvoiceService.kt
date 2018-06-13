package com.s63d.erinvoiceservice.services

import com.s63d.erinvoiceservice.clients.TripClient
import com.s63d.erinvoiceservice.clients.UserClient
import com.s63d.erinvoiceservice.clients.VehicleClient
import com.s63d.erinvoiceservice.domain.db.Invoice
import com.s63d.erinvoiceservice.domain.db.InvoiceLine
import com.s63d.erinvoiceservice.domain.db.InvoiceLinePart
import com.s63d.erinvoiceservice.domain.db.Rate
import com.s63d.erinvoiceservice.domain.rest.InvoiceStatus
import com.s63d.erinvoiceservice.domain.rest.Trip
import com.s63d.erinvoiceservice.repositories.InvoiceLinePartRepository
import com.s63d.erinvoiceservice.repositories.InvoiceRepository
import com.s63d.erinvoiceservice.repositories.RateRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*


@Service
class InvoiceService(private val invoiceRepository: InvoiceRepository, private val invoiceLinePartRepository: InvoiceLinePartRepository, private val tripClient: TripClient, private val vehicleClient: VehicleClient, private val userClient: UserClient) {

    fun getVehicles(authHeader: String) {
        val listvehicles = vehicleClient.getCurrentVehicles(authHeader).content
        listvehicles.forEach {
            if (it.carTrackerId != null) {
                generateInvoice(authHeader,it.ownerId, it.id)
            }
        }
    }

    private fun generateInvoice(authHeader: String, userId: Long, vehicleId: String) {
        val invoiceLines : List<InvoiceLine> = generateInvoiceLines(authHeader, vehicleId)

        val user = userClient.getUserById(userId, authHeader)
        if (invoiceLines.isNotEmpty()) invoiceRepository.save(Invoice(user = user, vehicleId = vehicleId, date = Date(), status = InvoiceStatus.OPEN, lines = invoiceLines, price = invoiceLines.map { it.price }.sum(), distance = invoiceLines.sumBy { it.distance }))
    }

    private fun generateInvoiceLines(authHeader: String, vehicleId: String): List<InvoiceLine> {
        var trips: List<Trip> = listOf()
        trips += tripClient.getById(authHeader, vehicleId)?.content ?: throw Exception("could not find trips")
        var invoiceLines: List<InvoiceLine> = listOf()
        trips.forEach {
            val parts = invoiceLinePartRepository.findByTripId(it.tripId)
            invoiceLines += InvoiceLine(tripId = it.tripId, distance = parts.sumBy { it.distance }, price = parts.map { it.price }.sum(), parts = parts)
        }
        return invoiceLines
    }

    fun getInvoices(userId: Long): List<Invoice> {
        return invoiceRepository.findByUserId(userId)
    }

    fun getInvoiceById(id: Long) : Invoice {
        return invoiceRepository.findById(id).get()
    }

    fun getAllInvoices(pageable: Pageable) = invoiceRepository.findAll(pageable);

}