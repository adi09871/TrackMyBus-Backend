package com.trackmybus.backend.service

import com.trackmybus.backend.dto.StudentLoginRequest
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
            password = request.password
        )

        val savedStudent = studentRepository.save(student)

        println(savedStudent)

        return "Student Registered Successfully"
    }
fun login (
    request: StudentLoginRequest
): String{
    val student = studentRepository.findByEmail(
        request.email
    )
        ?: return "Email not found!"
    return if (
        student.password == request.password
    )
    {
        "Login Successful!"
    }
    else {
        "Wrong password!"
    }}

}