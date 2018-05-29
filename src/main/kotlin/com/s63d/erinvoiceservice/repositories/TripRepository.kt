package com.s63d.erinvoiceservice.repositories

import com.s63d.erinvoiceservice.domain.Trip
import org.springframework.data.repository.CrudRepository

interface TripRepository : CrudRepository<Trip, Long>