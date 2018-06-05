package com.s63d.erinvoiceservice.services

import com.s63d.erinvoiceservice.clients.TripClient
import com.s63d.erinvoiceservice.clients.UserClient
import com.s63d.erinvoiceservice.clients.VehicleClient
import com.s63d.erinvoiceservice.domain.db.Invoice
import com.s63d.erinvoiceservice.domain.db.InvoiceLine
import com.s63d.erinvoiceservice.domain.db.Rate
import com.s63d.erinvoiceservice.domain.rest.InvoiceStatus
import com.s63d.erinvoiceservice.domain.rest.SimpleVehicle
import com.s63d.erinvoiceservice.domain.rest.Trip
import com.s63d.erinvoiceservice.repositories.InvoiceLineRepository
import com.s63d.erinvoiceservice.repositories.InvoiceRepository
import com.s63d.erinvoiceservice.repositories.RateRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
import java.text.DecimalFormat



@Service
class InvoiceService(private val invoiceRepository: InvoiceRepository, private val rateRepository: RateRepository, private val invoiceLineRepository: InvoiceLineRepository,  private val tripClient: TripClient, private val vehicleClient: VehicleClient, private val userClient: UserClient) {
    fun getVehicles(authHeader: String) {
        val listvehicles = vehicleClient.getCurrentVehicles(authHeader).content
        listvehicles.forEach {
            if (it.carTrackerId != null) {
                val vehicleRate = rateRepository.findById(it.rate).get()
                generateInvoice(authHeader,it.ownerId, it.carTrackerId, vehicleRate)
            }
        }
    }

    private fun generateInvoice(authHeader: String, userId: Long, trackId: String, rate: Rate) {
        val invoiceLines : List<InvoiceLine> = generateInvoiceLines(authHeader, trackId, rate)
        val price = calculateTripPrice(invoiceLines)
        if (invoiceLines.isNotEmpty()) invoiceRepository.save(Invoice(userId = userId, date = Date(), status = InvoiceStatus.OPEN, rate = rate, invoiceLine = invoiceLines, price = price))
    }

    private fun calculateTripPrice(invoiceLines: List<InvoiceLine>) : Double {
        var price = 0.00
        invoiceLines.forEach {
            price += it.length / 1000 * it.rate.price
        }

        return price
    }

    private fun generateInvoiceLines(authHeader: String, trackId: String, rate: Rate): List<InvoiceLine> {
        var trips: List<Trip> = listOf()
        trips += tripClient.getById(authHeader, trackId)?: throw Exception("could not find trips")
        var invoiceLines: List<InvoiceLine> = listOf()
        trips.forEach {
            invoiceLines += InvoiceLine(tripId = it.tripId, length = it.length, rate = rate)
        }
        return invoiceLines
    }

    fun getInvoice(userId: Long): List<Invoice> {
        return invoiceRepository.findByUserId(userId)
    }

    fun getAllInvoices(pageable: Pageable) = invoiceRepository.findAll(pageable);

}