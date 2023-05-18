package com.example.banks_sprin_kotlin.service

import com.example.banks_sprin_kotlin.model.User
import java.util.*

interface UserService {
    fun addUser(user: User): User
    fun deleteUser(id: Long)
    fun getUserById(id: Long): Optional<User>
    fun getUsers(): Collection<User>
    fun getUserByFirstName(firstname: String): List<User>
    fun updateUser(id: Long, user: User): User
}