package com.example.banks_spring_kotlin.model

import jakarta.persistence.*


@Entity
@Table(name = "users")
class User(
    @Column(nullable = false, unique = true) var email: String = "",

    @Column(nullable = false) var password: String = "",

    @ElementCollection(fetch = FetchType.EAGER) @Column(nullable = false) var roles: Set<String> = emptySet()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}