package com.s63d.erinvoiceservice.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/test")
class ExampleController {

    @GetMapping
    fun indexGet() = "Hey, from the index"

    @GetMapping("/path")
    fun pathGet() = "Hey from /path"

}