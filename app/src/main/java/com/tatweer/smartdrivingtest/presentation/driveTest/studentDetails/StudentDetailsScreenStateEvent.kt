package com.tatweer.smartdrivingtest.presentation.driveTest.studentDetails

import com.tatweer.smartdrivingtest.presentation.base.StateEvent

sealed interface StudentDetailsScreenStateEvent : StateEvent {
    data object NavigateToStudentVerification : StudentDetailsScreenStateEvent
}