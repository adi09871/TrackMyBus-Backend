package com.trackmybus.backend.service

import com.trackmybus.backend.dto.SaveStopsRequest
import com.trackmybus.backend.entity.Stop
import com.trackmybus.backend.repository.StopRepository
import jakarta.persistence.Id
import org.springframework.stereotype.Service

@Service
class StopService(
    private val
    StopRepository: StopRepository
) {

    fun savestop(
        request: SaveStopsRequest
    ): String {
        StopRepository.deleteByBusId(
            request.busId
        )
        request.stops.forEachIndexed { index, stopName ->

            val Stop = Stop(
                busId = request.busId,
                stopName = stopName,
                stopOrder = index + 1
            )
            StopRepository.save(Stop)
        }
        return "Stops saved successfully"

    }

    fun getStopsByBusId(
        busId: Long

    ): List<Stop> {
        return StopRepository.findByBusId(busId)

    }


}