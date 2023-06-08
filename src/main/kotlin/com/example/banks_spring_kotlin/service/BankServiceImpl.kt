package com.example.banks_spring_kotlin.service

import com.example.banks_spring_kotlin.exception.AccountNotFound
import com.example.banks_spring_kotlin.model.Bank
import com.example.banks_spring_kotlin.repository.BankRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BankServiceImpl : BankService {

    @Autowired
    private lateinit var bankRepository: BankRepository

    override fun retrieveBanks(): Collection<Bank> {
        return bankRepository.findAll()
    }

    override fun retrieveBank(accountNumber: String): Bank {
        val bank = bankRepository.findByAccountNumber(accountNumber)
        if (bank.isPresent) {
            return bank.get()
        } else {
            throw AccountNotFound("Bank not found with this $accountNumber")
        }
    }

    override fun saveBank(bank: Bank): Bank {
        bankRepository.save(bank)
        return bank
    }

    override fun updateBankInfo(bank: Bank): Bank {
        return bank.id?.let { id ->
            bankRepository.findById(id).orElseThrow { AccountNotFound("Bank Not found with ID: $id") }
        }?.apply {
            accountNumber = bank.accountNumber
            trust = bank.trust
            transactionFee = bank.transactionFee
            bankRepository.save(this)
        } ?: throw AccountNotFound("cant update the bank info with ID: ${bank.id}")
    }

    override fun deleteBank(id: Long) {
        if (bankRepository.existsById(id)) {
            bankRepository.deleteById(id)
        } else {
            throw AccountNotFound("Bank not found with ID: $id")
        }
    }
}