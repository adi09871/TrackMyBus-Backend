package com.trackmybus.backend.service

import com.trackmybus.backend.dto.SaveTokenRequest
import com.trackmybus.backend.dto.StudentLoginRequest
import com.trackmybus.backend.dto.StudentLoginResponse
import com.trackmybus.backend.dto.StudentRegisterRequest
import com.trackmybus.backend.entity.Notification
import com.trackmybus.backend.entity.Student
import com.trackmybus.backend.entity.StudentToken
import com.trackmybus.backend.repository.NotificationRepository
import com.trackmybus.backend.repository.StudentRepository
import com.trackmybus.backend.repository.StudentTokenRepository
import org.springframework.stereotype.Service
import com.trackmybus.backend.dto.StudentProfileResponse
@Service
class StudentService(
    private val studentRepository: StudentRepository,
    private val studentTokenRepository: StudentTokenRepository,
    private val notificationRepository: NotificationRepository

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

    }
    fun saveFcmToken(
        request: SaveTokenRequest
    ) {

        val existingToken =
            studentTokenRepository.findByStudentId(
                request.studentId
            )

        if (existingToken != null) {

            studentTokenRepository.save(
                existingToken.copy(
                    fcmToken = request.fcmToken
                )
            )

        } else {

            studentTokenRepository.save(
                StudentToken(
                    studentId = request.studentId,
                    fcmToken = request.fcmToken
                )
            )
        }
    }

    fun getStudentToken(
        studentId: Long
    ): String? {

        val studentToken =
            studentTokenRepository.findByStudentId(
                studentId
            )

        return studentToken?.fcmToken
    }
    fun getNotifications(
        studentId: Long
    ): List<Notification> {

        return notificationRepository
            .findAllByStudentIdOrderByCreatedAtDesc(
                studentId
            )
    }
    fun getStudentProfile(
        studentId: Long
    ): StudentProfileResponse? {

        val student =
            studentRepository.findById(studentId)
                .orElse(null)
                ?: return null

        return StudentProfileResponse(
            id = student.id,
            name = student.name,
            email = student.email,
            collegeId = student.collegeId,
            busId = student.busId
        )
    }


    }