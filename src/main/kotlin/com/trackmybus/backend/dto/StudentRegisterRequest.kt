package com.trackmybus.backend.dto


data class StudentRegisterRequest(
    val name: String,
    val email: String,
    val password: String,

)