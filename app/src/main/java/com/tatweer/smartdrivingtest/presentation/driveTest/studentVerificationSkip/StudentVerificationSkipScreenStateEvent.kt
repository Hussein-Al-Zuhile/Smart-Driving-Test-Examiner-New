package com.tatweer.smartdrivingtest.presentation.driveTest.studentVerificationSkip

import com.tatweer.smartdrivingtest.presentation.base.StateEvent

sealed interface StudentVerificationSkipScreenStateEvent : StateEvent {
    data object NavigateToRunningTest : StudentVerificationSkipScreenStateEvent
    data object NavigateUp : StudentVerificationSkipScreenStateEvent
}