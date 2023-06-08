package com.example.banks_spring_kotlin.service

import com.example.banks_spring_kotlin.model.Bank
import com.example.banks_spring_kotlin.repository.BankRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class BankServiceTests {

    @Mock
    private lateinit var bankRepository: BankRepository

    @InjectMocks
    private val bankService = BankServiceImpl()

    @Test
    fun `retrieve all banks test`() {
        `when`(bankRepository.findAll()).thenReturn(listOf(
            Bank("12345", 4.5, 10),
            Bank("67890", 3.2, 20)
        ))

        val banks = bankService.retrieveBanks()

        assertNotNull(banks)
        assertEquals(2, banks.size)
    }

    @Test
    fun `retrieve bank by account number test`() {
        val bank = Bank("12345, 4.5, 10").apply { id = 1L }
        `when`(bankRepository.findByAccountNumber("12345")).thenReturn(Optional.of(bank))

        val retrievedBank = bankService.retrieveBank("12345")

        assertNotNull(retrievedBank)
        assertEquals(1L, retrievedBank.id)
    }


    @Test
    fun `save bank test`() {
        val bank = Bank("12345", 4.5, 10)
        `when`(bankRepository.save(bank)).thenReturn(bank.apply { id = 1L })

        val savedBank = bankService.saveBank(bank)

        assertNotNull(savedBank)
        assertEquals(1L, savedBank.id)
    }

    @Test
    fun `update bank info test`() {
        val bank = Bank("12345", 4.5, 10).apply { id = 1L }
        `when`(bankRepository.findById(1L)).thenReturn(Optional.of(bank))

        val updatedBank = bankService.updateBankInfo(Bank(accountNumber = "12345",  trust = 4.5,transactionFee = 20).apply { id = 1L })

        assertNotNull(updatedBank)
        assertEquals(1L, updatedBank.id)
        assertEquals(4.5, updatedBank.trust)
        assertEquals(20, updatedBank.transactionFee)
    }

    @Test
    fun `test delete bank`() {
        // given
        `when`(bankRepository.existsById(1L)).thenReturn(true)

        // when
        bankService.deleteBank(1L)

        // then
        `when`(bankRepository.existsById(1L)).thenReturn(false)
        assertFalse(bankRepository.existsById(1L))
    }


}

