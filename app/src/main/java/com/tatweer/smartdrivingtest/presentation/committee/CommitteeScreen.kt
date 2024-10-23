package com.tatweer.smartdrivingtest.presentation.committee

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tatweer.smartdrivingtest.presentation.committee.studentList.StudentListScreen
import com.tatweer.smartdrivingtest.presentation.committee.studentList.StudentListScreenStateEvent
import com.tatweer.smartdrivingtest.presentation.committee.studentList.StudentListViewModel
import com.tatweer.smartdrivingtest.presentation.committee.testRouteSelection.TestRouteSelectionScreen
import com.tatweer.smartdrivingtest.presentation.committee.testRouteSelection.TestRouteSelectionScreenEvent
import com.tatweer.smartdrivingtest.presentation.committee.testRouteSelection.TestRouteSelectionViewModel
import com.tatweer.smartdrivingtest.presentation.committee.vehicleInspectionForm.VehicleInspectionFormScreen
import com.tatweer.smartdrivingtest.presentation.committee.vehicleInspectionForm.VehicleInspectionFormScreenEvent
import com.tatweer.smartdrivingtest.presentation.committee.vehicleInspectionForm.VehicleInspectionFormViewModel
import com.tatweer.smartdrivingtest.presentation.home.HomeScreenEvent
import com.tatweer.smartdrivingtest.presentation.main.LocalAnimatedContentScope
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.utils.ConsumeEach
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Composable
fun CommitteeScreen(
    onEvent: (HomeScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
) {

    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = CommitteeScreenRoute.VehicleInspectionFormRoute,
        modifier,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(500)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(500)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(500)
            )
        },
    ) {

        composable<CommitteeScreenRoute.VehicleInspectionFormRoute> {
            val viewModel: VehicleInspectionFormViewModel = koinViewModel()

            VehicleInspectionFormScreen(viewModel.vehicleInspectionFormScreenState, { event ->
                when (event) {
                    VehicleInspectionFormScreenEvent.OnCancelClicked -> navController.navigateUp()
                    VehicleInspectionFormScreenEvent.OnSendClicked -> navController.navigate(
                        CommitteeScreenRoute.RouteSelectionRoute
                    )

                    else -> viewModel.onEvent(event)
                }
            })
        }

        composable<CommitteeScreenRoute.RouteSelectionRoute> {
            val viewModel: TestRouteSelectionViewModel = koinViewModel()
            TestRouteSelectionScreen(viewModel.testRouteSelectionScreenState,
                {
                    when (it) {
                        TestRouteSelectionScreenEvent.OnConfirmClicked -> {
                            navController.navigate(CommitteeScreenRoute.StudentListRoute)
                        }

                        else -> viewModel.onEvent(it)
                    }
                })
        }

        composable<CommitteeScreenRoute.StudentListRoute> {
            val viewModel: StudentListViewModel = koinViewModel()
            viewModel.singleStateEventChannel.ConsumeEach {
                when (it) {
                    is StudentListScreenStateEvent.OnStudentTestStarted -> {
                        onEvent(HomeScreenEvent.OnStudentTestStarted(it.student))
                    }
                }
            }
            StudentListScreen(viewModel.studentListScreenState, viewModel::onEvent)
        }


    }
}

sealed interface CommitteeScreenRoute {
    @Serializable
    data object VehicleInspectionFormRoute : CommitteeScreenRoute

    @Serializable
    data object StudentListRoute : CommitteeScreenRoute

    @Serializable
    data object RouteSelectionRoute : CommitteeScreenRoute
}

@Preview(device = Devices.TABLET)
@Composable
private fun StudentListScreenPreview() {
    AppTheme {
        CommitteeScreen({})
    }
}