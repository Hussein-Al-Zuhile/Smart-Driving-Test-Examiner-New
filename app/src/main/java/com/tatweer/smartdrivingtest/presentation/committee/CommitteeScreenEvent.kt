package com.tatweer.smartdrivingtest.presentation.committee

import com.tatweer.smartdrivingtest.domain.model.Student

sealed interface CommitteeScreenEvent {
    data class OnStudentsFetched(val students: List<Student>) : CommitteeScreenEvent
}