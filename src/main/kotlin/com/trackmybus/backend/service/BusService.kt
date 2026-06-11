package com.trackmybus.backend.service

import com.trackmybus.backend.dto.BusCreateRequest
import com.trackmybus.backend.entity.Bus
import com.trackmybus.backend.repository.BusRepository
import org.springframework.stereotype.Service

@Service
class BusService(
    private val busRepository: BusRepository
) {

    fun createBus(
        request: BusCreateRequest
    ): String {

        val existingBus = busRepository.findByBusNumber(
            request.busNumber
        )

        if (existingBus != null) {
            return "Bus already exists!"
        }

        val bus = Bus(
            busNumber = request.busNumber,
            seatCapacity = request.seatCapacity,
            routeName = request.routeName,
            driverId = request.driverId
        )

        busRepository.save(bus)

        return "Bus Created Successfully"
    }

    fun getBusesByDriverId(
        driverId: Long
    ): List<Bus> {

        return busRepository.findAllByDriverId(driverId)
    }}