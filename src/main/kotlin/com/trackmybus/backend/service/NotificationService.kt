package com.trackmybus.backend.service

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import org.springframework.stereotype.Service

@Service
class NotificationService {

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
}