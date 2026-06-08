package com.trackmybus.backend.repository

import com.trackmybus.backend.entity.Driver
import com.trackmybus.backend.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface DriverRepository : JpaRepository<Driver, Long> {

    fun findByEmail(email: String): Driver?
}