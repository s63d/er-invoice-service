package com.s63d.erinvoiceservice.repositories

import com.s63d.erinvoiceservice.domain.SimpleUser
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<SimpleUser, Long>