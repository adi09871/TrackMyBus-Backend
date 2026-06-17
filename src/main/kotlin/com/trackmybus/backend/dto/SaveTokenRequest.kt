package com.trackmybus.backend.dto

data class SaveTokenRequest(

    val studentId: Long,

    val fcmToken: String
)
