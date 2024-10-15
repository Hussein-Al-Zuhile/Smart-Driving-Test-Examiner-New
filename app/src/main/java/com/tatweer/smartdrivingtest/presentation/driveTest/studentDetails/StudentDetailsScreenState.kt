package com.tatweer.smartdrivingtest.presentation.driveTest.studentDetails

import com.tatweer.smartdrivingtest.domain.model.Student
import com.tatweer.smartdrivingtest.domain.model.StudentStatus
import com.tatweer.smartdrivingtest.domain.model.TestRoute

data class StudentDetailsScreenState(
    val student: Student,
    val route: TestRoute,
) {
    companion object {
        // TODO: Remove it when the logic is clear
        val Initial = StudentDetailsScreenState(Student(
            id = 7865,
            name = "Peter Raymond",
            studentId = "noster",
            status = StudentStatus.NotStarted
        ), TestRoute(1))
    }
}