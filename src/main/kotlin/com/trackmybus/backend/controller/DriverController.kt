package com.trackmybus.backend.controller

import com.trackmybus.backend.dto.DriverLoginRequest
import com.trackmybus.backend.dto.DriverRegisterRequest
import com.trackmybus.backend.service.DriverService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/drivers")
class DriverController (private val driverService: DriverService) {

    @PostMapping("/register")

    fun register (@RequestBody request: DriverRegisterRequest):
   String
    {
        return driverService.regsiter(request)
    }

    @PostMapping("/login")
    fun login   (@RequestBody request: DriverLoginRequest)
    : String {
        return driverService.login(request)
    }
}