package com.trackmybus.backend.service

import com.trackmybus.backend.dto.LocationUpdateRequest
import com.trackmybus.backend.entity.Location
import com.trackmybus.backend.repository.LocationRepository
import org.springframework.stereotype.Service

@Service
class LocationService(

    private val locationRepository: LocationRepository
) {

    fun updateLocation(
        request: LocationUpdateRequest
    ): String {

        val location = Location(
            busId = request.busId,
            latitude = request.latitude,
            longitude = request.longitude,
            timestamp = System.currentTimeMillis()
        )

        locationRepository.save(location)

        return "Location Updated"
    }

    fun getCurrentLocation(
        busId: Long
    ): Location? {

        return locationRepository
            .findTopByBusIdOrderByTimestampDesc(
                busId
            )
    }
}