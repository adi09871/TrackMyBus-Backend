package com.trackmybus.backend.controller

import com.trackmybus.backend.dto.SaveTokenRequest
import com.trackmybus.backend.dto.StudentLoginRequest
import com.trackmybus.backend.dto.StudentLoginResponse
import com.trackmybus.backend.dto.StudentRegisterRequest
import com.trackmybus.backend.service.NotificationService
import com.trackmybus.backend.service.StudentService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/students")
class StudentsController(
    private val studentService: StudentService,
    private val notificationService: NotificationService

) {

    @PostMapping("/register")
    fun register(
        @RequestBody request: StudentRegisterRequest
    ): String {
        return studentService.register(request)
    }

    @PostMapping("/login")
    fun login(
        @RequestBody request: StudentLoginRequest
    ): StudentLoginResponse? {

        return studentService.login(
            request
        )
    }

    @PostMapping("/save-token")
    fun saveToken(
        @RequestBody request: SaveTokenRequest
    ): String {

        studentService.saveFcmToken(
            request
        )

        return "Token Saved"
    }
    @PostMapping("/test-notification/{studentId}")
    fun testNotification(
        @PathVariable studentId: Long
    ): String {

        val token =
            studentService.getStudentToken(
                studentId
            )

        if (token == null) {
            return "Token not found"
        }

        notificationService.sendNotification(
            token = token,
            title = "TrackMyBus",
            body = "First notification from backend 🚍"
        )

        return "Notification sent"
    }
}