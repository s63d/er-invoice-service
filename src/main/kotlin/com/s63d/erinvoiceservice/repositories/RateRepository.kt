package com.s63d.erinvoiceservice.repositories

import com.s63d.erinvoiceservice.domain.Rate
import org.springframework.data.repository.CrudRepository

interface RateRepository : CrudRepository<Rate, String>