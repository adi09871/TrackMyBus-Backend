package com.trackmybus.backend.repository

import com.trackmybus.backend.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface StudentRepository : JpaRepository<Student, Long> {

    fun findByEmail(email: String): Student?
    fun findAllByBusId(
        busId: Long
    ): List<Student>
}