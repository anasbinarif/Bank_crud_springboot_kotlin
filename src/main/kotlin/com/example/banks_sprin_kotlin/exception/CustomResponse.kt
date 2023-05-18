package com.example.banks_sprin_kotlin.exception

class CustomResponse(
    var status: Int? = null,
    var message: String? = null,
    var timeStamp: Long? = null) {
}