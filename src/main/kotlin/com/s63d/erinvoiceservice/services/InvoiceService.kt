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
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service
import java.util.*
import org.springframework.beans.factory.annotation.Autowired




@Service
class InvoiceService(private val invoiceRepository: InvoiceRepository, private val invoiceLinePartRepository: InvoiceLinePartRepository, private val rateRepository: RateRepository, private val tripClient: TripClient, private val vehicleClient: VehicleClient) {

    fun getVehicles(authHeader: String) {
        val listvehicles = vehicleClient.getCurrentVehicles(authHeader).content
        listvehicles.forEach {
            if (it.carTrackerId != null) {
                val vehicleRate = rateRepository.findById(it.rate).get()
                generateInvoice(authHeader,it.ownerId, it.id, vehicleRate)
            }
        }
    }

    private fun generateInvoice(authHeader: String, userId: Long, vehicleId: String, rate: Rate) {
        val invoiceLines : List<InvoiceLine> = generateInvoiceLines(authHeader, vehicleId)
//        val price = calculateTripPrice(invoiceLines)
        if (invoiceLines.isNotEmpty()) invoiceRepository.save(Invoice(userId = userId, date = Date(), status = InvoiceStatus.OPEN, rate = rate, invoiceLines = invoiceLines, price = invoiceLines.map { it.price }.sum()))
    }
//
//    private fun calculateTripPrice(invoiceLines: List<InvoiceLine>) : Double {
//        var price = 0.00
//        invoiceLines.forEach {
//            price += it.length / 1000 * it.rate.price
//        }
//
//        return price
//    }
//
    private fun generateInvoiceLines(authHeader: String, vehicleId: String): List<InvoiceLine> {
        var trips: List<Trip> = listOf()
        trips += tripClient.getById(authHeader, vehicleId)?.content ?: throw Exception("could not find trips")
        var invoiceLines: List<InvoiceLine> = listOf()
        trips.forEach {
            val parts = invoiceLinePartRepository.findByTripid(it.tripId.toString())
            invoiceLines += InvoiceLine(tripId = it.tripId, length = parts.map { it.length }.sum(), price = parts.map { it.price }.sum(), invoiceLineParts = parts)
        }
        return invoiceLines
    }

    fun getInvoice(userId: Long): List<Invoice> {
        return invoiceRepository.findByUserId(userId)
    }

    fun getInvoiceLinePart(tripid: String) : List<InvoiceLinePart> {
        return invoiceLinePartRepository.findByTripid(tripid)
    }

    fun getAllInvoices(pageable: Pageable) = invoiceRepository.findAll(pageable);

}