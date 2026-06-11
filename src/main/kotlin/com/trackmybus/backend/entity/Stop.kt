package com.trackmybus.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "stops")
data class Stop(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val Id: Long = 0,
    val busId:Long,
    val stopName: String,
    val stopOrder: Int

)

