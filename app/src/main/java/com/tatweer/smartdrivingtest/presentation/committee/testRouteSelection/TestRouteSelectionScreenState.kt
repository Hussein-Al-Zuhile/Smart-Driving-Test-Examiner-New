package com.tatweer.smartdrivingtest.presentation.committee.testRouteSelection

import com.tatweer.smartdrivingtest.domain.model.TestRoute
import com.tatweer.smartdrivingtest.presentation.base.BaseUIState

data class TestRouteSelectionScreenState(
    val testRoutes: List<TestRoute> = listOf(TestRoute(1), TestRoute(2), TestRoute(3), TestRoute(4), TestRoute(5), TestRoute(6)),
    val selectedTestRoute: TestRoute? = null,
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
) : BaseUIState()
