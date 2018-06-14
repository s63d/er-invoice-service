package com.s63d.erinvoiceservice.paypal

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/paypal")
class PayPalController(private val client: PayPalClient) {

    @PostMapping("/payment")
    fun createPayment(@RequestParam sum: String) = client.createPayment(sum)

    @PostMapping("/complete")
    fun completePayment(request: HttpServletRequest): Map<String, Any> = client.completePayment(request)
}