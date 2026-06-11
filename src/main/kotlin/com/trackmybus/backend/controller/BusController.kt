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
    }}