package com.tatweer.smartdrivingtest.presentation.login

import com.tatweer.smartdrivingtest.presentation.base.StateEvent


interface LoginScreenStateEvent:StateEvent {
    data class OnLoginSucceeded(val token: String) : LoginScreenStateEvent
    data class OnLoginFailed(val errorMessage: String) : LoginScreenStateEvent
}