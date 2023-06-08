package com.example.banks_spring_kotlin.service

import com.example.banks_spring_kotlin.model.User
import com.example.banks_spring_kotlin.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl : UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun addUser(user: User): User {
        userRepository.save(user)
        return user
    }

    override fun deleteUser(id: Long) {
        TODO("Not yet implemented")
    }

    override fun getUserById(id: Long): Optional<User> {
        val user = userRepository.findById(id)
        if (user == null) {
            throw NoSuchElementException("user not found!!!")
        } else {
            return user
        }
    }

    override fun getUsers(): Collection<User> {
        return userRepository.findAll()
    }

    override fun getUserByFirstName(firstname: String): List<User> {
        TODO("Not yet implemented")
    }

    override fun updateUser(id: Long, user: User): User {
        TODO("Not yet implemented")
    }
}