package com.s63d.erinvoiceservice.domain.db

import com.fasterxml.jackson.annotation.JsonAlias
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class User(@Column(name = "user_id") val id: Long, val firstName: String, val lastName: String, val address: String, val postal: String, val city: String, val email: String)