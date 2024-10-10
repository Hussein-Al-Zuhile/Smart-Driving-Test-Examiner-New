package com.tatweer.smartdrivingtest.presentation.committee.testRouteSelection

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tatweer.smartdrivingtest.presentation.base.BaseViewModel

class TestRouteSelectionViewModel : BaseViewModel<TestRouteSelectionScreenStateEvent>() {

    var testRouteSelectionScreenState by mutableStateOf(TestRouteSelectionScreenState())
        private set

    fun onEvent(event: TestRouteSelectionScreenEvent) {
        when (event) {
            is TestRouteSelectionScreenEvent.OnTestRouteSelected -> {
                testRouteSelectionScreenState =
                    testRouteSelectionScreenState.copy(selectedTestRoute = event.testRoute)
            }

            TestRouteSelectionScreenEvent.OnConfirmClicked -> {
                // Handle confirm click
            }
        }

    }
}