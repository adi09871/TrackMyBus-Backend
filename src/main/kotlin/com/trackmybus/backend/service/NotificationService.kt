package com.trackmybus.backend.service

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.trackmybus.backend.entity.Notification
import com.trackmybus.backend.repository.NotificationRepository
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val notificationRepository: NotificationRepository
) {

    fun sendNotification(
        token: String,
        title: String,
        body: String
    ) {

        val message =
            Message.builder()
                .setToken(token)
                .putData("title", title)
                .putData("body", body)
                .build()

        val response =
            FirebaseMessaging
                .getInstance()
                .send(message)

        println("NOTIFICATION SENT = $response")
    }
    fun saveNotification(
        studentId: Long,
        title: String,
        message: String
    ) {

        notificationRepository.save(
            Notification(
                studentId = studentId,
                title = title,
                message = message
            )
        )
    }
}