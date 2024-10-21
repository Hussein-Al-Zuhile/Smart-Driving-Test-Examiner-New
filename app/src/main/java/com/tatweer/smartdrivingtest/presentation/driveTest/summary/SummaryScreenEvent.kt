package com.tatweer.smartdrivingtest.presentation.driveTest.summary

sealed interface SummaryScreenEvent {
    data object OnBackClicked : SummaryScreenEvent
    data class OnSubmitClicked(val summary: String) : SummaryScreenEvent
}