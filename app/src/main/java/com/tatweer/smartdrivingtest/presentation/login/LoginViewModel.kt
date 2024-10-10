package com.tatweer.smartdrivingtest.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.tatweer.smartdrivingtest.presentation.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel<LoginScreenStateEvent>() {

    var loginScreenState by mutableStateOf(LoginScreenState())
        private set

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.OnMilitaryIDChanged -> {
                loginScreenState = loginScreenState.copy(militaryID = event.militaryID)
            }

            is LoginScreenEvent.OnPasswordChanged -> {
                loginScreenState = loginScreenState.copy(password = event.password)
            }

            is LoginScreenEvent.OnPasswordVisibilityToggled -> {
                loginScreenState =
                    loginScreenState.copy(isPasswordVisible = !loginScreenState.isPasswordVisible)
            }

            is LoginScreenEvent.OnSignInClicked -> {
                viewModelScope.launch {
                    loginScreenState = loginScreenState.copy(isLoading = true)
                    delay(3000)
                    loginScreenState = loginScreenState.copy(isLoading = false)
                    _singleStateEventChannel.send(LoginScreenStateEvent.OnLoginSucceeded("token"))
                }
            }

            is LoginScreenEvent.OnCardSignInDetected -> {
                // Handle card sign-in logic
            }
        }
    }
}