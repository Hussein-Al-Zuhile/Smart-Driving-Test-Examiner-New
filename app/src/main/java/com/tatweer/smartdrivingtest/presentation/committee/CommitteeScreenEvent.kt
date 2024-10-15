package com.tatweer.smartdrivingtest.presentation.committee

import com.tatweer.smartdrivingtest.domain.model.Student

sealed interface CommitteeScreenEvent {
    data class OnStudentTestStarted(val student: Student) : CommitteeScreenEvent
    data object OnStudentTestFinished : CommitteeScreenEvent
}