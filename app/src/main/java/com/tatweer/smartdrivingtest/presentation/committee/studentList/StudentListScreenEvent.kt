package com.tatweer.smartdrivingtest.presentation.committee.studentList

import com.tatweer.smartdrivingtest.domain.model.Student

sealed interface StudentListScreenEvent {
    data object OnStartTestClicked : StudentListScreenEvent
    data class OnStudentsFetched(val students: List<Student>) : StudentListScreenEvent
    data class OnStudentSelected(val student: Student) : StudentListScreenEvent

    data class OnStudentAttendOnBusClicked(val student: Student) : StudentListScreenEvent
    data class OnStudentAbsentClicked(val student: Student) : StudentListScreenEvent
    data class OnStudentTestStarted(val student: Student) : StudentListScreenEvent

    data object OnStudentDeselected : StudentListScreenEvent
}