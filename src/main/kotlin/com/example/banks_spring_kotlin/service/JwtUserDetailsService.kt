package com.example.banks_spring_kotlin.service

import com.example.banks_spring_kotlin.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class JwtUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)

        return org.springframework.security.core.userdetails.User
            .withUsername(user.email)
            .password(user.password)
            .authorities(user.roles.map { role -> SimpleGrantedAuthority("ROLE_$role") })
            .build()
    }
}