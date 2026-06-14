package com.trackmybus.backend.service

import com.trackmybus.backend.dto.SaveStopsRequest
import com.trackmybus.backend.entity.Stop
import com.trackmybus.backend.repository.StopRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StopService(
    private val stopRepository: StopRepository
) {
    fun getStopsByBusId(
        busId: Long
    ): List<Stop> {

        return stopRepository.findByBusId(busId)
    }

    @Transactional
    fun savestop(
        request: SaveStopsRequest
    ): String {

        stopRepository.deleteByBusId(request.busId)

        request.stops.forEachIndexed { index, stopName ->

            val stop = Stop(
                busId = request.busId,
                stopName = stopName,
                stopOrder = index + 1
            )

            stopRepository.save(stop)
        }

        return "Stops saved successfully"
    }
}