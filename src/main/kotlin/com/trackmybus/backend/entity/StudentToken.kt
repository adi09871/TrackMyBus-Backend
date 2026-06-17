package com.trackmybus.backend.entity


import jakarta.persistence.*

@Entity
@Table(name = "student_tokens")
data class StudentToken(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val studentId: Long = 0,

    @Column(columnDefinition = "TEXT")
    val fcmToken: String = ""
)