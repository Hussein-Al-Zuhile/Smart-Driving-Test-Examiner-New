package com.tatweer.smartdrivingtest.presentation.base

abstract class BaseUIState(
    open val isLoading: Boolean = false,
    open val errorMessage: String? = null
)