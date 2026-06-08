package com.trackmybus.backend.controller

import com.trackmybus.backend.dto.StudentLoginRequest
import com.trackmybus.backend.dto.StudentRegisterRequest
import com.trackmybus.backend.service.StudentService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/students")
class StudentsController(
    private val studentService: StudentService
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
    ): String {
        return studentService.login(request)
    }
}