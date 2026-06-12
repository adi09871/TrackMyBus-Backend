package com.trackmybus.backend.controller

import com.trackmybus.backend.dto.LocationUpdateRequest
import com.trackmybus.backend.entity.Location
import com.trackmybus.backend.service.LocationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/location")
class LocationController(

    private val locationService: LocationService
) {

    @PostMapping("/update")
    fun updateLocation(
        @RequestBody request: LocationUpdateRequest
    ): String {

        return locationService.updateLocation(
            request
        )
    }

    @GetMapping("/{busId}")
    fun getCurrentLocation(
        @PathVariable busId: Long
    ): Location? {

        return locationService
            .getCurrentLocation(busId)
    }
}