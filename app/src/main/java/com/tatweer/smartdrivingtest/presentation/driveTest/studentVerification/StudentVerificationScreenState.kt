package com.tatweer.smartdrivingtest.presentation.driveTest.studentVerification

import com.tatweer.smartdrivingtest.domain.model.Student

data class StudentVerificationScreenState(
    val student: Student,
    val note: String,
) {
    companion object {
        val Initial = StudentVerificationScreenState(Student(1), "")
    }
}
