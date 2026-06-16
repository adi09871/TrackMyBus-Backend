package com.trackmybus.backend.dto

data class StudentLoginResponse(

    val id: Long,

    val name: String,

    val busId: Long?
)