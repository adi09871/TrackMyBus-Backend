package com.trackmybus.backend.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class FirebaseConfig {

    @PostConstruct
    fun initialize() {

        if (FirebaseApp.getApps().isNotEmpty()) {
            return
        }

        val serviceAccount =
            ClassPathResource(
                "trackmybus-4ac67-firebase-adminsdk-fbsvc-c5160b97ac.json"
            ).inputStream

        val options =
            FirebaseOptions.builder()
                .setCredentials(
                    GoogleCredentials.fromStream(
                        serviceAccount
                    )
                )
                .build()

        FirebaseApp.initializeApp(
            options
        )

        println(
            "FIREBASE INITIALIZED"
        )
    }
}