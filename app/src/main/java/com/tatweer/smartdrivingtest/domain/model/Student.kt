package com.tatweer.smartdrivingtest.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val id: Int,
    val name: String,
    val studentId: String,
    val status: StudentStatus
) {
    companion object {
        val Initial = Student(
            id = 9623,
            name = "Joesph Maldonado",
            studentId = "vis",
            status = StudentStatus.NotStarted
        )
    }
}


@Serializable
enum class StudentStatus {
    NotStarted,
    Absent,
    Complete,
    Incomplete
}
