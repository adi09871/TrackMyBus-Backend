package com.trackmybus.backend.dto


data class SaveStopsRequest(
    val busId: Long,
    val stops: List<String>
)