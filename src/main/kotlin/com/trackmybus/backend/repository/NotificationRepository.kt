package com.trackmybus.backend.repository

import com.trackmybus.backend.entity.Notification
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepository :
    JpaRepository<Notification, Long> {

    fun findAllByStudentIdOrderByCreatedAtDesc(
        studentId: Long
    ): List<Notification>
}