package com.s63d.erinvoiceservice.domain.rest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class SimplePage<T> (val content: List<T> = emptyList())