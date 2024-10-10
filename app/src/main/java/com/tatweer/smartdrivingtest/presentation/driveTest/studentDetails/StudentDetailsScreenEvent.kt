package com.tatweer.smartdrivingtest.presentation.driveTest.studentDetails

import com.tatweer.smartdrivingtest.domain.model.TestRoute

sealed interface StudentDetailsScreenEvent {
    data object OnVerifyStudentClicked : StudentDetailsScreenEvent
    data object OnAbsentClicked : StudentDetailsScreenEvent
    data object OnChangeRouteClicked : StudentDetailsScreenEvent
    data class OnRouteSelected(val testRoute: TestRoute) : StudentDetailsScreenEvent
}