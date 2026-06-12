package com.trackmybus.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "locations")
data class Location(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val busId: Long,

    val latitude: Double,

    val longitude: Double,

    val timestamp: Long
)