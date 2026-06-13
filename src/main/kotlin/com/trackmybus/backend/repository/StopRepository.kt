package com.trackmybus.backend.repository

import com.trackmybus.backend.entity.Stop
import org.springframework.data.jpa.repository.JpaRepository

interface StopRepository : JpaRepository<Stop, Long> {

    fun findByBusId(busId: Long): List<Stop>

    fun deleteByBusId(
        busId: Long
    )
}