package com.trackmybus.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "students")
data class Student(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String = "",

    @Column(unique = true)
    val email: String = "",

    val password: String = "",

    val collegeId: String = ""
)