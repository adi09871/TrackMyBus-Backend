package com.trackmybus.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "buses")
data class Bus(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val busNumber: String,

    val seatCapacity: Int,

    val routeName: String,

    val driverId: Long,
    var currentOccupancy: Int = 0,
    var isTripActive: Boolean = false

)