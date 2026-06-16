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
    fun getAllBuses(): List<Bus> {

        return busRepository.findAll()
    }
    fun getBusById(
        busId: Long
    ): Bus? {

        return busRepository.findById(busId)
            .orElse(null)
    }

    fun increaseOccupancy(
        busId: Long
    ): Bus? {

        val bus = busRepository.findById(busId)
            .orElse(null) ?: return null

        if (bus.currentOccupancy < bus.seatCapacity) {

            bus.currentOccupancy++

            busRepository.save(bus)
        }

        return bus
    }

    fun decreaseOccupancy(
        busId: Long
    ): Bus? {

        val bus = busRepository.findById(busId)
            .orElse(null) ?: return null

        if (bus.currentOccupancy > 0) {

            bus.currentOccupancy--

            busRepository.save(bus)
        }

        return bus
    }

    fun starTrip(busId: Long): Bus? {

        val bus = busRepository.findById(busId).orElse(null) ?: return null

bus.isTripActive = true
        return busRepository.save(bus)

    }


    fun stopTrip(busId: Long): Bus? {
        val bus = busRepository.findById(busId).orElse(null) ?: return null

        bus.isTripActive = false
        return busRepository.save(bus)
    }

}
