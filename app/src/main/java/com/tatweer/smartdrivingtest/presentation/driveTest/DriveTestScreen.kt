@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.tatweer.smartdrivingtest.presentation.driveTest

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.driveTest.addManualFault.AddManualFaultDialog
import com.tatweer.smartdrivingtest.presentation.driveTest.addManualFault.AddManualFaultDialogStateEvent
import com.tatweer.smartdrivingtest.presentation.driveTest.addManualFault.AddManualFaultViewModel
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
import com.tatweer.smartdrivingtest.presentation.driveTest.summary.SummaryScreen
import com.tatweer.smartdrivingtest.presentation.home.HomeScreenEvent
import com.tatweer.smartdrivingtest.presentation.home.HomeScreenState
import com.tatweer.smartdrivingtest.presentation.main.LocalAnimatedContentScope
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.utils.ConsumeEach
import org.koin.androidx.compose.koinViewModel


@Composable
fun DriveTestScreen(
    homeScreenState: HomeScreenState,
    onHomeScreenEvent: (HomeScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = DriveTestScreenDestination.UserDetails,
        modifier,
        enterTransition = { slideIn { IntOffset(it.width, 0) } },
        exitTransition = { slideOut { IntOffset(-it.width, 0) } },
        popEnterTransition = { slideIn { IntOffset(-it.width, 0) } },
        popExitTransition = { slideOut { IntOffset(it.width, 0) } },
    ) {
        composable<DriveTestScreenDestination.UserDetails> {
            val viewModel: StudentDetailsViewModel = koinViewModel()
            viewModel.singleStateEventChannel.ConsumeEach {
                when (it) {
                    StudentDetailsScreenStateEvent.NavigateToStudentVerification -> {
                        navController.navigate(DriveTestScreenDestination.StudentVerification)
                    }
                }
            }

            CompositionLocalProvider(LocalAnimatedContentScope provides this) {
                StudentDetailsScreen(
                    homeScreenState,
                    onHomeScreenEvent,
                    onStudentDetailsScreenEvent = viewModel::onEvent,
                )
            }
        }
        composable<DriveTestScreenDestination.StudentVerification> {
            val viewModel: StudentVerificationViewModel = koinViewModel()
            viewModel.singleStateEventChannel.ConsumeEach {
                when (it) {
                    StudentVerificationScreenStateEvent.NavigateToStudentVerificationSkip ->
                        navController.navigate(DriveTestScreenDestination.StudentVerificationSkip)

                    StudentVerificationScreenStateEvent.NavigateUp -> navController.navigateUp()
                }
            }
            CompositionLocalProvider(LocalAnimatedContentScope provides this) {
                StudentVerificationScreen(
                    homeScreenState,
                    viewModel.studentVerificationScreenState,
                    onEvent = viewModel::onEvent,
                )
            }
        }

        composable<DriveTestScreenDestination.StudentVerificationSkip> {
            val viewModel: StudentVerificationSkipViewModel = koinViewModel()
            viewModel.singleStateEventChannel.ConsumeEach {
                when (it) {
                    StudentVerificationSkipScreenStateEvent.NavigateToRunningTest ->
                        navController.navigate(DriveTestScreenDestination.RunningTest)

                    StudentVerificationSkipScreenStateEvent.NavigateUp -> navController.navigateUp()
                }
            }
            CompositionLocalProvider(LocalAnimatedContentScope provides this) {
                StudentVerificationSkipScreen(homeScreenState, viewModel.state, viewModel::onEvent)
            }
        }

        composable<DriveTestScreenDestination.RunningTest> {
            val viewModel: RunningTestViewModel = koinViewModel()
            viewModel.singleStateEventChannel.ConsumeEach {
                when (it) {
                    RunningTestScreenStateEvent.NavigateToAddManualFaultDialog ->
                        navController.navigate(DriveTestScreenDestination.AddManualFault)

                    RunningTestScreenStateEvent.NavigateToEmergencyStop ->
                        navController.navigate(DriveTestScreenDestination.EmergencyStop)

                    RunningTestScreenStateEvent.NavigateToSummary ->
                        navController.navigate(DriveTestScreenDestination.Summary)
                }
            }
            RunningTestScreen(viewModel.state, viewModel::onEvent)
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

        composable<DriveTestScreenDestination.Summary> {
            SummaryScreen(onHomeScreenEvent)
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