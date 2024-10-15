package com.tatweer.smartdrivingtest.presentation.committee

import com.tatweer.smartdrivingtest.domain.model.Student
import com.tatweer.smartdrivingtest.presentation.base.StateEvent

interface CommitteeScreenStateEvent : StateEvent {
    data class OnStudentTestStarted(val student: Student) : CommitteeScreenStateEvent
    data object OnStudentTestFinished : CommitteeScreenStateEvent
}