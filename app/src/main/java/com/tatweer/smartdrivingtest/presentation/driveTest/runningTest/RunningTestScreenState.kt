package com.tatweer.smartdrivingtest.presentation.driveTest.runningTest

import com.tatweer.smartdrivingtest.domain.model.Fault

data class RunningTestScreenState(
    val minutes: Int = 0,
    val seconds: Int = 0,
    val detectedFaults: List<Fault> = emptyList(),
    val isTestStarted: Boolean = false,
)
