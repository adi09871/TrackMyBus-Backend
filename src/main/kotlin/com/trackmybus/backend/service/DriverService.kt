package com.trackmybus.backend.service

import com.trackmybus.backend.dto.DriverLoginRequest
import com.trackmybus.backend.dto.DriverLoginResponse
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
    fun login(
        request: DriverLoginRequest
    ): DriverLoginResponse {

        val driver = driverRepository.findByEmail(
            request.email
        ) ?: return DriverLoginResponse(
            message = "Email not found",
            driverId = -1
        )

        return if (
            driver.password == request.password
        ) {
            DriverLoginResponse(
                message = "Login Successful!",
                driverId = driver.id
            )
        } else {
            DriverLoginResponse(
                message = "Wrong password",
                driverId = -1
            )
        }
    }}