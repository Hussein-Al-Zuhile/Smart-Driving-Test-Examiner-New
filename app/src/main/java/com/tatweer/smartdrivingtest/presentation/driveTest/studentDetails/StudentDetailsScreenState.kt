package com.tatweer.smartdrivingtest.presentation.driveTest.studentDetails

import com.tatweer.smartdrivingtest.domain.model.Student
import com.tatweer.smartdrivingtest.domain.model.TestRoute

data class StudentDetailsScreenState(
    val student: Student,
    val route: TestRoute,
) {
    companion object {
        // TODO: Remove it when the logic is clear
        val Initial = StudentDetailsScreenState(Student(1), TestRoute(1))
    }
}