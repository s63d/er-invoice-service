package com.s63d.erinvoiceservice.clients

import com.s63d.erinvoiceservice.domain.db.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping

@FeignClient("users", url = "\${urls.account-service}/api")
 @RequestMapping("/users")
 interface UserClient {

     @GetMapping("{id}")
     fun getUserById(@PathVariable id: Long, @RequestHeader("Authorization") token: String) : User

}