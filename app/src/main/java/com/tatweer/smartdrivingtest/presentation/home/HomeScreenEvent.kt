package com.tatweer.smartdrivingtest.presentation.home

import com.tatweer.smartdrivingtest.domain.model.Student

sealed interface HomeScreenEvent {
    data class OnStudentTestStarted(val student: Student) : HomeScreenEvent
    data class OnStudentTestSubmitted(val summary: String) : HomeScreenEvent
}