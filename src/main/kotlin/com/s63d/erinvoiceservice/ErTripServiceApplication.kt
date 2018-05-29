package com.s63d.ertripservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class ErInvoiceServiceApplication

fun main(args: Array<String>) {
    runApplication<ErInvoiceServiceApplication>(*args)
}
