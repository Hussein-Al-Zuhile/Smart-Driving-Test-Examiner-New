package com.tatweer.smartdrivingtest.presentation.driveTest

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.driveTest.addManualFault.AddManualFaultDialog
import com.tatweer.smartdrivingtest.presentation.driveTest.emergencyStop.EmergencyStopScreen
import com.tatweer.smartdrivingtest.presentation.driveTest.emergencyStop.EmergencyStopViewModel
import com.tatweer.smartdrivingtest.presentation.driveTest.runningTest.RunningTestScreen
import com.tatweer.smartdrivingtest.presentation.driveTest.runningTest.RunningTestScreenStateEvent
import com.tatweer.smartdrivingtest.presentation.driveTest.runningTest.RunningTestViewModel
import com.tatweer.smartdrivingtest.presentation.driveTest.studentDetails.StudentDetailsScreen
import com.tatweer.smartdrivingtest.presentation.driveTest.studentDetails.StudentDetailsScreenStateEvent
import com.tatweer.smartdrivingtest.presentation.driveTest.studentDetails.StudentDetailsViewModel
import com.tatweer.smartdrivingtest.presentation.driveTest.studentVerification.StudentVerificationScreen
import com.tatweer.smartdrivingtest.presentation.driveTest.studentVerification.StudentVerificationScreenStateEvent
import com.tatweer.smartdrivingtest.presentation.driveTest.studentVerification.StudentVerificationViewModel
import com.tatweer.smartdrivingtest.presentation.driveTest.studentVerificationSkip.StudentVerificationSkipScreen
import com.tatweer.smartdrivingtest.presentation.driveTest.studentVerificationSkip.StudentVerificationSkipScreenStateEvent
import com.tatweer.smartdrivingtest.presentation.driveTest.studentVerificationSkip.StudentVerificationSkipViewModel
import com.tatweer.smartdrivingtest.presentation.home.HomeScreenEvent
import com.tatweer.smartdrivingtest.presentation.home.HomeScreenState
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.utils.consumeEach
import org.koin.androidx.compose.koinViewModel

@Composable
fun DriveTestScreen(
    homeScreenState: HomeScreenState,
    onHomeScreenEvent: (HomeScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = DriveTestScreenDestination.UserDetails, modifier) {
        composable<DriveTestScreenDestination.UserDetails> {
            val viewModel: StudentDetailsViewModel = koinViewModel()
            viewModel.singleStateEventChannel.consumeEach {
                when (it) {
                    StudentDetailsScreenStateEvent.NavigateToStudentVerification -> {
                        navController.navigate(DriveTestScreenDestination.StudentVerification)
                    }
                }
            }
            StudentDetailsScreen(
                homeScreenState,
                onHomeScreenEvent,
                onStudentDetailsScreenEvent = viewModel::onEvent
            )
        }
        composable<DriveTestScreenDestination.StudentVerification> {
            val viewModel: StudentVerificationViewModel = koinViewModel()
            viewModel.singleStateEventChannel.consumeEach {
                when (it) {
                    StudentVerificationScreenStateEvent.NavigateToStudentVerificationSkip ->
                        navController.navigate(DriveTestScreenDestination.StudentVerificationSkip)

                    StudentVerificationScreenStateEvent.NavigateUp -> navController.navigateUp()
                }
            }
            StudentVerificationScreen(
                viewModel.studentVerificationScreenState, onEvent = viewModel::onEvent
            )
        }

        composable<DriveTestScreenDestination.StudentVerificationSkip> {
            val viewModel: StudentVerificationSkipViewModel = koinViewModel()
            viewModel.singleStateEventChannel.consumeEach {
                when (it) {
                    StudentVerificationSkipScreenStateEvent.NavigateToRunningTest ->
                        navController.navigate(DriveTestScreenDestination.RunningTest)

                    StudentVerificationSkipScreenStateEvent.NavigateUp -> navController.navigateUp()
                }
            }
            StudentVerificationSkipScreen(viewModel.state, viewModel::onEvent)
        }

        composable<DriveTestScreenDestination.RunningTest> {
            val viewModel: RunningTestViewModel = koinViewModel()
            viewModel.singleStateEventChannel.consumeEach {
                when (it) {
                    RunningTestScreenStateEvent.NavigateToAddManualFaultDialog ->
                        navController.navigate(DriveTestScreenDestination.AddManualFault)

                    RunningTestScreenStateEvent.NavigateToEmergencyStop ->
                        navController.navigate(DriveTestScreenDestination.EmergencyStop)
                }
            }
            RunningTestScreen(viewModel.state, viewModel::onEvent)
        }

        dialog<DriveTestScreenDestination.AddManualFault>(
            dialogProperties = DialogProperties(
                usePlatformDefaultWidth = false,
            )
        ) {
            AddManualFaultDialog()
        }

        dialog<DriveTestScreenDestination.EmergencyStop>(
            dialogProperties = DialogProperties(
                usePlatformDefaultWidth = false,
            )
        ) {
            val viewModel: EmergencyStopViewModel = koinViewModel()
            EmergencyStopScreen(
                viewModel.emergencyStopScreenState,
                onEvent = viewModel::onEvent
            )
        }
    }
}

@PreviewTablet
@Composable
private fun DriveTestScreenPreview() {
    AppTheme {
        DriveTestScreen(HomeScreenState(), {})
    }
}