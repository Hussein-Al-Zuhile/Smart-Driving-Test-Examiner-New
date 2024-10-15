package com.tatweer.smartdrivingtest.presentation.home

import com.tatweer.smartdrivingtest.domain.model.Student
import com.tatweer.smartdrivingtest.presentation.base.BaseUIState

data class HomeScreenState(
    val startedStudent: Student? = null,
) : BaseUIState()