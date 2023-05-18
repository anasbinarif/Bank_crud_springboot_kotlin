package com.example.banks_sprin_kotlin.service

import com.example.banks_sprin_kotlin.model.Bank
import com.example.banks_sprin_kotlin.repository.BankRepository
import com.example.banks_sprin_kotlin.service.BankServiceImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import kotlin.NoSuchElementException

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
        `when`(bankRepository.findByAccountNumber("12345")).thenReturn(
            Bank("12345", 4.5, 10).apply { id = 1L }
        )

        val bank = bankService.retrieveBank("12345")

        assertNotNull(bank)
        assertEquals(1L, bank.id)
    }

    @Test
    fun `retrieve bank by invalid account number test`() {
        `when`(bankRepository.findByAccountNumber(anyString())).thenReturn(null)

        assertThrows(NoSuchElementException::class.java) {
            bankService.retrieveBank("invalid_account_number")
        }
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

