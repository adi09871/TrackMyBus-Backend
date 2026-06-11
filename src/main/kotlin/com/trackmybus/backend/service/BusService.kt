package com.trackmybus.backend.service

import com.trackmybus.backend.dto.BusCreateRequest
import com.trackmybus.backend.dto.BusCreateResponse
import com.trackmybus.backend.entity.Bus
import com.trackmybus.backend.repository.BusRepository
import org.springframework.stereotype.Service

@Service
class BusService(
    private val busRepository: BusRepository
) {

    fun createBus(
        request: BusCreateRequest
    ): BusCreateResponse {

        val existingBus = busRepository.findByBusNumber(
            request.busNumber
        )

        if (existingBus != null) {

            return BusCreateResponse(
                busId = existingBus.id,
                message = "Bus already exists!"
            )
        }

        val bus = Bus(
            busNumber = request.busNumber,
            seatCapacity = request.seatCapacity,
            routeName = request.routeName,
            driverId = request.driverId
        )

        val savedBus = busRepository.save(bus)

        return BusCreateResponse(
            busId = savedBus.id,
            message = "Bus Created Successfully"
        )
    }

    fun getBusesByDriverId(
        driverId: Long
    ): List<Bus> {

        return busRepository.findAllByDriverId(driverId)
    }
}