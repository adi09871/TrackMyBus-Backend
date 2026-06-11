package com.trackmybus.backend.repository

import com.trackmybus.backend.entity.Bus
import org.springframework.data.jpa.repository.JpaRepository

interface BusRepository : JpaRepository<Bus, Long> {

    fun findAllByDriverId(driverId: Long): List<Bus>

    fun findByBusNumber(busNumber: String): Bus?
}