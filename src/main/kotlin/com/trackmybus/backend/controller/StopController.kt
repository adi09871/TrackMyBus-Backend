package com.trackmybus.backend.controller

import com.trackmybus.backend.dto.SaveStopsRequest
import com.trackmybus.backend.entity.Stop
import com.trackmybus.backend.service.StopService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/stops")
class StopController(
    private val stopService: StopService
) {

    @GetMapping("/test")
    fun test(): String {
        return "STOP CONTROLLER WORKING"
    }

    @PostMapping("/save")
    fun saveStops(
        @RequestBody request: SaveStopsRequest
    ): String {
        return stopService.savestop(request)
    }

    @GetMapping("/bus/{busId}")
    fun getStopsByBusId(
        @PathVariable busId: Long
    ): List<Stop> {
        return stopService.getStopsByBusId(busId)
    }
}