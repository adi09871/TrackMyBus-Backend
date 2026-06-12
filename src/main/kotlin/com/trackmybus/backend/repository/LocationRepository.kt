package com.trackmybus.backend.repository

import com.trackmybus.backend.entity.Location
import org.springframework.data.jpa.repository.JpaRepository

interface LocationRepository :
    JpaRepository<Location, Long> {

    fun findTopByBusIdOrderByTimestampDesc(
        busId: Long
    ): Location?
}