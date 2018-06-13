package com.s63d.erinvoiceservice.security

import org.springframework.context.annotation.Primary
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
@Primary
class UserDetailService : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return User(username,username, emptyList())
    }
}