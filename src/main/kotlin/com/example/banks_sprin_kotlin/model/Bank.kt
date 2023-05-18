package com.example.banks_sprin_kotlin.model

import jakarta.persistence.*


@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["accountNumber"])])
class Bank(
    @Column(unique = true)
    var accountNumber: String = "",
    var trust: Double = 0.0,
    var transactionFee: Int = 0
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}


