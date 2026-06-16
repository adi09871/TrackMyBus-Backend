package com.trackmybus.backend.controller

import com.trackmybus.backend.dto.BusCreateRequest
import com.trackmybus.backend.dto.BusCreateResponse
import com.trackmybus.backend.entity.Bus
import com.trackmybus.backend.service.BusService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/buses")
class BusController(
    private val busService: BusService
) {

    @PostMapping("/create")
    fun createBus(
        @RequestBody request: BusCreateRequest
    ): BusCreateResponse {

        return busService.createBus(request)
    }

    @GetMapping("/driver/{driverId}")
    fun getBusesByDriverId(
        @PathVariable driverId: Long
    ): List<Bus> {

        return busService.getBusesByDriverId(driverId)
    }

    @PostMapping("/increase/{busId}")
    fun increaseOccupancy(
        @PathVariable busId: Long
    ): Bus? {

        return busService.increaseOccupancy(busId)
    }

    @PostMapping("/decrease/{busId}")
    fun decreaseOccupancy(
        @PathVariable busId: Long
    ): Bus? {

        return busService.decreaseOccupancy(busId)
    }

    @PostMapping("/start-trip/{busId}")
fun startTrip(@PathVariable busId: Long): Bus? {

        return busService.starTrip(busId)
    }

        @PostMapping ("/stop-trip/{busId}")
 fun stopTrip(@PathVariable busId: Long): Bus? {
     return busService.stopTrip(busId)
 }
    @GetMapping("/{busId}")
    fun getBusById(
        @PathVariable busId: Long
    ): Bus? {

        return busService.getBusById(busId)
    }

    @GetMapping
    fun getAllBuses(): List<Bus> {

        return busService.getAllBuses()
    }
}