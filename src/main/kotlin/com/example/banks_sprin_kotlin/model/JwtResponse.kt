package com.example.banks_sprin_kotlin.model

import java.io.Serializable

data class JwtResponse(val jwttoken: String) : Serializable {
    companion object {
        private const val serialVersionUID = -8091879091924046844L
    }
}