package com.s63d.erinvoiceservice.paypal

import com.paypal.api.payments.*
import com.paypal.base.rest.APIContext
import com.paypal.base.rest.PayPalRESTException
import lombok.extern.java.Log
import org.reflections.Reflections.log
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Log
@Component
class PayPalClient {
    @Value("\${paypal.client.id}")
    lateinit var clientId: String
    @Value("\${paypal.client.secret}")
    lateinit var clientSecret: String

    fun createPayment(sum: String): Map<String, Any> {
        val response = mutableMapOf<String, Any>()
        val amount = Amount("EUR", sum)
        val transaction = Transaction()
        transaction.amount = amount
        val transactions = listOf(transaction)

        val payer = Payer()
        payer.paymentMethod = "paypal"

        val payment = Payment("sale", payer)
        payment.transactions = transactions

        val redirectUrls = RedirectUrls()
        redirectUrls.cancelUrl = "https://ersols.online/paymentCanceled"
        redirectUrls.returnUrl = "https://ersols.online/"
        payment.redirectUrls = redirectUrls

        try {
            val context = APIContext(clientId, clientSecret, "sandbox")
            val createdPayment = payment.create(context)
            val redirectUrl = createdPayment.links.first { it.rel == "approval_url" }.href
            response["status"] = "success"
            response["redirect_url"] = redirectUrl
        } catch (e: PayPalRESTException) {
            logError(e)
        }
        return response
    }

    fun completePayment(req: HttpServletRequest): Map<String, Any> {
        val response = mutableMapOf<String, Any>()
        val payment = Payment()
        payment.id = req.getParameter("paymentId")

        val paymentExecution = PaymentExecution()
        paymentExecution.payerId = req.getParameter("PayerID")

        try {
            val context = APIContext(clientId, clientSecret, "sandbox")
            val createdPayment = payment.execute(context, paymentExecution)
            response["status"] = "success"
            response["payment"] = createdPayment
        } catch (e: PayPalRESTException) {
            logError(e)
        }
        return response
    }

    private fun logError(e: Exception) {
        log!!.error(e.message, e)
    }
}