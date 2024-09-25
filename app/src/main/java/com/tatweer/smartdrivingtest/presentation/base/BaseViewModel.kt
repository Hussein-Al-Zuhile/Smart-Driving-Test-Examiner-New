package com.tatweer.smartdrivingtest.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.stateIn

abstract class BaseViewModel : ViewModel() {

    protected suspend fun <T> Flow<T>.asStateFlowInViewModelScope() = stateIn(viewModelScope)
}