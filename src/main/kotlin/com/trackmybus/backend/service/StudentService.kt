package com.trackmybus.backend.service

import com.trackmybus.backend.dto.StudentLoginRequest
import com.trackmybus.backend.dto.StudentLoginResponse
import com.trackmybus.backend.dto.StudentRegisterRequest
import com.trackmybus.backend.entity.Student
import com.trackmybus.backend.repository.StudentRepository
import org.springframework.stereotype.Service

@Service
class StudentService(
    private val studentRepository: StudentRepository
){

    fun register(request: StudentRegisterRequest): String {

        println("REGISTER HIT")
        println(request)

        if(studentRepository.findByEmail(request.email) != null){
            return "Email already registered!"
        }

        val student = Student(
            name = request.name,
            email = request.email,
            password = request.password,
            collegeId = request.collegeId,
            busId = request.busId
        )

        val savedStudent = studentRepository.save(student)

        println(savedStudent)

        return "Student Registered Successfully"
    }
    fun login(
        request: StudentLoginRequest
    ): StudentLoginResponse? {

        println("LOGIN HIT")
        println("EMAIL = ${request.email}")

        val student =
            studentRepository.findByEmail(
                request.email
            )

        println("STUDENT = $student")

        if (student == null) {
            println("EMAIL NOT FOUND")
            return null
        }

        if (student.password != request.password) {
            println("PASSWORD MISMATCH")
            return null
        }

        println("LOGIN SUCCESS")

        return StudentLoginResponse(
            id = student.id,
            name = student.name,
            busId = student.busId
        )
    }}