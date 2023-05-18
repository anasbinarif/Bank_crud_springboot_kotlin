package com.example.banks_sprin_kotlin.controller

import com.example.banks_sprin_kotlin.model.User
import com.example.banks_sprin_kotlin.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/user")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getUsers(): Collection<User> = userService.getUsers()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addUser(@RequestBody user: User) = userService.addUser(user)
}