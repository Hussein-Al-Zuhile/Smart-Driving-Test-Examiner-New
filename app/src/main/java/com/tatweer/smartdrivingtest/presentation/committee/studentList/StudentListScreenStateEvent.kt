package com.tatweer.smartdrivingtest.presentation.committee.studentList

import com.tatweer.smartdrivingtest.domain.model.Student
import com.tatweer.smartdrivingtest.presentation.base.StateEvent

interface StudentListScreenStateEvent : StateEvent {
    data class OnStudentTestStarted(val student: Student) : StudentListScreenStateEvent
}