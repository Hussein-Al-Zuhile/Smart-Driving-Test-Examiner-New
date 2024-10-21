package com.tatweer.smartdrivingtest.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tatweer.smartdrivingtest.presentation.base.BaseViewModel

class HomeViewModel : BaseViewModel<HomeScreenStateEvent>() {

    var homeScreenState by mutableStateOf(HomeScreenState())
        private set

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnStudentTestStarted -> {
                homeScreenState = homeScreenState.copy(startedStudent = event.student)
            }

            is HomeScreenEvent.OnStudentTestSubmitted -> {
                homeScreenState = homeScreenState.copy(startedStudent = null)
                _singleStateEventChannel.sendInViewModelScope(HomeScreenStateEvent.PopBackToStudentList)
            }
        }
    }
}