package com.trackmybus.backend.dto

data class LocationUpdateRequest(

    val busId: Long,

    val latitude: Double,

    val longitude: Double
)