package com.example.banks_spring_kotlin.repository

import com.example.banks_spring_kotlin.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User
}