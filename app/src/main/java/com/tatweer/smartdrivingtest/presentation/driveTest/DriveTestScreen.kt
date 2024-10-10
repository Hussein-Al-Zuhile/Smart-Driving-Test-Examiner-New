package com.tatweer.smartdrivingtest.presentation.driveTest

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.driveTest.emergencyStop.EmergencyStopScreen
import com.tatweer.smartdrivingtest.presentation.driveTest.emergencyStop.EmergencyStopViewModel
import com.tatweer.smartdrivingtest.presentation.driveTest.studentDetails.StudentDetailsScreen
import com.tatweer.smartdrivingtest.presentation.driveTest.studentDetails.StudentDetailsScreenEvent
import com.tatweer.smartdrivingtest.presentation.driveTest.studentDetails.StudentDetailsViewModel
import com.tatweer.smartdrivingtest.presentation.driveTest.studentVerification.StudentVerificationScreen
import com.tatweer.smartdrivingtest.presentation.driveTest.studentVerification.StudentVerificationScreenEvent
import com.tatweer.smartdrivingtest.presentation.driveTest.studentVerification.StudentVerificationViewModel
import com.tatweer.smartdrivingtest.presentation.driveTest.studentVerificationSkip.StudentVerificationSkipScreen
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DriveTestScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = DriveTestScreenDestination.UserDetails, modifier) {
        composable<DriveTestScreenDestination.UserDetails> {
            val viewModel: StudentDetailsViewModel = koinViewModel()
            StudentDetailsScreen(
                viewModel.studentDetailsScreenState, onEvent = {
                    when (it) {
                        StudentDetailsScreenEvent.OnVerifyStudentClicked -> {
                            navController.navigate(DriveTestScreenDestination.EmergencyStop)
                        }

                        else -> viewModel.onEvent(it)
                    }
                }
            )
        }
        composable<DriveTestScreenDestination.UserVerification> {
            val viewModel: StudentVerificationViewModel = koinViewModel()
            StudentVerificationScreen(viewModel.studentVerificationScreenState, onEvent = {
                when (it) {
                    StudentVerificationScreenEvent.OnSkipClicked -> {
                        navController.navigate(DriveTestScreenDestination.StudentVerificationSkip)
                    }

                    else -> viewModel.onEvent(it)
                }
            })
        }

        composable<DriveTestScreenDestination.StudentVerificationSkip> {
            StudentVerificationSkipScreen()
        }

        dialog<DriveTestScreenDestination.EmergencyStop>(dialogProperties = DialogProperties(usePlatformDefaultWidth = false, decorFitsSystemWindows = false)) {
            val viewModel: EmergencyStopViewModel = koinViewModel()
            EmergencyStopScreen(viewModel.emergencyStopScreenState,
                onEvent = {
                    when(it) {
                        else -> {
                            viewModel.onEvent(it)
                        }
                    }
                })
        }
    }
}

@PreviewTablet
@Composable
private fun DriveTestScreenPreview() {
    AppTheme {
        DriveTestScreen()
    }
}