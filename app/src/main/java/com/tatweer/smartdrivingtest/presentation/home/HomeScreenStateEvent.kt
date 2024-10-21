package com.tatweer.smartdrivingtest.presentation.home

import com.tatweer.smartdrivingtest.presentation.base.StateEvent

sealed interface HomeScreenStateEvent : StateEvent {
    data object PopBackToStudentList : HomeScreenStateEvent
}