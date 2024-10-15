package com.tatweer.smartdrivingtest.presentation.driveTest.studentVerification

import com.tatweer.smartdrivingtest.presentation.base.StateEvent

sealed interface StudentVerificationScreenStateEvent : StateEvent {
    data object NavigateToStudentVerificationSkip : StudentVerificationScreenStateEvent
    data object NavigateUp : StudentVerificationScreenStateEvent
}