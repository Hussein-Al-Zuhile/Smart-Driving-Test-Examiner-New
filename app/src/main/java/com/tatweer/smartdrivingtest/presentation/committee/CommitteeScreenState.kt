package com.tatweer.smartdrivingtest.presentation.committee

import com.tatweer.smartdrivingtest.domain.model.Student
import com.tatweer.smartdrivingtest.presentation.base.BaseUIState

data class CommitteeScreenState(
    val startedStudent: Student? = null,
) : BaseUIState()