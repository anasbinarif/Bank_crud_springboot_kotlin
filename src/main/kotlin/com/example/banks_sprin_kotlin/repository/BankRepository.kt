package com.example.banks_sprin_kotlin.repository

import com.example.banks_sprin_kotlin.model.Bank
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional


interface BankRepository : JpaRepository<Bank, Long> {
    fun findByAccountNumber(accountNumber: String): Optional<Bank>
    override fun findById(id: Long): Optional<Bank>
}