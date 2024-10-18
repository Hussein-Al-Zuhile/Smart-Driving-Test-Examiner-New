package com.tatweer.smartdrivingtest.presentation.driveTest.studentVerification

import com.tatweer.smartdrivingtest.domain.model.Student
import com.tatweer.smartdrivingtest.domain.model.StudentStatus

data class StudentVerificationScreenState(
    val student: Student,
    val note: String,
) {
    companion object {
        val Initial = StudentVerificationScreenState(
            Student(
                id = 4855,
                name = "Isidro O'Donnell",
                emiratesId = "disputationi",
                status = StudentStatus.NotStarted
            ), ""
        )
    }
}
