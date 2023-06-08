package com.example.banks_spring_kotlin.service

import com.example.banks_spring_kotlin.model.Bank

interface BankService {
    fun retrieveBanks(): Collection<Bank>
    fun retrieveBank(accountNumber: String): Bank
    fun saveBank(bank: Bank): Bank
    fun updateBankInfo(bank: Bank): Bank
    fun deleteBank(id: Long)
}