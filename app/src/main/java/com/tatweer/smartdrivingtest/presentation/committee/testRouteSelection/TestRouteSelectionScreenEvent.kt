package com.tatweer.smartdrivingtest.presentation.committee.testRouteSelection

import com.tatweer.smartdrivingtest.domain.model.TestRoute

sealed interface TestRouteSelectionScreenEvent {
    data class OnTestRouteSelected(val testRoute: TestRoute) : TestRouteSelectionScreenEvent
    object OnConfirmClicked : TestRouteSelectionScreenEvent
}