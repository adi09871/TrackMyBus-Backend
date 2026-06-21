package com.trackmybus.backend.service

import com.trackmybus.backend.dto.BusCreateRequest
import com.trackmybus.backend.dto.BusCreateResponse
import com.trackmybus.backend.entity.Bus
import com.trackmybus.backend.repository.BusRepository
import com.trackmybus.backend.repository.StudentRepository
import com.trackmybus.backend.repository.StudentTokenRepository
import org.springframework.stereotype.Service

@Service
class BusService(
    private val busRepository: BusRepository,
    private val studentRepository: StudentRepository,
    private val studentTokenRepository: StudentTokenRepository,
    private val notificationService: NotificationService
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

        try {

            println("===== START_TRIP_SERVICE_CALLED =====")
            println("BUS_ID=$busId")

            val bus =
                busRepository.findById(busId)
                    .orElse(null)
                    ?: run {
                        println("BUS_NOT_FOUND")
                        return null
                    }

            println("BUS_FOUND=$bus")

            bus.isTripActive = true

            val updatedBus =
                busRepository.save(bus)

            println("TRIP_MARKED_ACTIVE")

            val students =
                studentRepository.findAllByBusId(busId)

            println("STUDENT_COUNT=${students.size}")

            val studentIds =
                students.map { it.id }

            val tokens =
                studentTokenRepository.findAllByStudentIdIn(studentIds)

            println("TOKEN_COUNT=${tokens.size}")

            tokens.forEach { token ->

                try {

                    notificationService.saveNotification(
                        studentId = token.studentId,
                        title = "Trip Started",
                        message = "Bus ${bus.busNumber} has started its trip 🚍"
                    )

                    notificationService.sendNotification(
                        token = token.fcmToken,
                        title = "Trip Started",
                        body = "Bus ${bus.busNumber} has started its trip 🚍"
                    )

                } catch (e: Exception) {

                    println("FCM_FAILED=${e.message}")
                }
            }

            println("START_TRIP_SUCCESS")

            return updatedBus

        } catch (e: Exception) {

            println("START_TRIP_EXCEPTION=${e.message}")

            e.printStackTrace()

            throw e
        }
    }


    fun stopTrip(busId: Long): Bus? {
        val bus = busRepository.findById(busId).orElse(null) ?: return null

        bus.isTripActive = false
        return busRepository.save(bus)
    }

}
