package com.tatweer.smartdrivingtest.presentation.login

import com.tatweer.smartdrivingtest.presentation.base.BaseUIState

data class LoginScreenState(
    val militaryID: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isSignInEnabled: Boolean = false,
    val isCardSignInEnabled: Boolean = false,
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null
) : BaseUIState()
