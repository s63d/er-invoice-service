package com.s63d.erinvoiceservice.domain

import javax.persistence.*

@Entity
@Table(name = "user")
data class SimpleUser(val firstName: String, val lastName: String, val address: String, val postal: String, val city: String, @Id val id: Long)