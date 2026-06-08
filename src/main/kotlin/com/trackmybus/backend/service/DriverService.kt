package com.trackmybus.backend.service

import com.trackmybus.backend.dto.DriverLoginRequest
import com.trackmybus.backend.dto.DriverRegisterRequest
import com.trackmybus.backend.entity.Driver
import com.trackmybus.backend.repository.DriverRepository
import org.springframework.stereotype.Service

@Service
class DriverService(
    private val driverRepository: DriverRepository


) {

    fun regsiter (
        request: DriverRegisterRequest
    ): String {

        if (driverRepository.findByEmail(request.email) != null) {
            return "Email is already registered"
        }
        val driver = Driver(
            name = request.name,
            email = request.email,
            password = request.password,
        )
        driverRepository.save(driver)
        return "Driver registered Sucessfully!"
    }
    fun login (
        request: DriverLoginRequest
    ): String {
        val driver = driverRepository.findByEmail(request.email)
        ?: return "Email is not found"

        return if (driver.password != request.password) {

            "login Sucessfully!"
        } else  { "wrong password" }
    }
}