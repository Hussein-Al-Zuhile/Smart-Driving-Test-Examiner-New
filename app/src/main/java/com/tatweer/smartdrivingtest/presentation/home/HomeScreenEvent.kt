package com.tatweer.smartdrivingtest.presentation.home

import com.tatweer.smartdrivingtest.domain.model.Student

interface HomeScreenEvent {
    data class OnStudentTestStarted(val student: Student) : HomeScreenEvent
    data class OnStudentTestFinished(val student: Student) : HomeScreenEvent
}