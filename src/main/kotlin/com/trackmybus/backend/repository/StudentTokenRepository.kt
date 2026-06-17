package com.trackmybus.backend.repository


import com.trackmybus.backend.entity.StudentToken
import org.springframework.data.jpa.repository.JpaRepository

interface StudentTokenRepository :
    JpaRepository<StudentToken, Long> {

    fun findByStudentId(
        studentId: Long
    ): StudentToken?
}