package com.example.banks_sprin_kotlin.controller

import com.example.banks_sprin_kotlin.model.Bank
import com.example.banks_sprin_kotlin.service.BankService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/banks")
class BankController(private val mongoBankService: BankService) {

    @GetMapping
    fun getBanks(): Collection<Bank> = mongoBankService.retrieveBanks()

    //
    @GetMapping("/{accountNumber}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    fun getBank(@PathVariable accountNumber: String): Bank {
        return mongoBankService.retrieveBank(accountNumber)
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody bank: Bank) = mongoBankService.saveBank(bank)

    //
    @PatchMapping
    fun updateBank(@RequestBody bank: Bank): Bank = mongoBankService.updateBankInfo(bank)

    //
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBank(@PathVariable id: Long): Unit = mongoBankService.deleteBank(id)
}