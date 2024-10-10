package com.tatweer.smartdrivingtest.presentation.committee

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tatweer.smartdrivingtest.presentation.committee.state.vehicleInspectionForm.VehicleInspectionFormScreen
import com.tatweer.smartdrivingtest.presentation.committee.studentList.StudentListScreen
import com.tatweer.smartdrivingtest.presentation.committee.studentList.StudentListScreenEvent
import com.tatweer.smartdrivingtest.presentation.committee.studentList.StudentListViewModel
import com.tatweer.smartdrivingtest.presentation.committee.testRouteSelection.TestRouteSelectionScreen
import com.tatweer.smartdrivingtest.presentation.committee.testRouteSelection.TestRouteSelectionScreenEvent
import com.tatweer.smartdrivingtest.presentation.committee.testRouteSelection.TestRouteSelectionViewModel
import com.tatweer.smartdrivingtest.presentation.committee.vehicleInspectionForm.VehicleInspectionFormScreenEvent
import com.tatweer.smartdrivingtest.presentation.committee.vehicleInspectionForm.VehicleInspectionFormViewModel
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Composable
fun CommitteeScreen(
    modifier: Modifier = Modifier,
) {
    val committeeViewModel: CommitteeViewModel = koinViewModel()

    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = CommitteeScreenRoute.VehicleInspectionFormRoute,
        modifier
    ) {

        composable<CommitteeScreenRoute.VehicleInspectionFormRoute> {
            val viewModel: VehicleInspectionFormViewModel = koinViewModel()
            viewModel.onEventReceived(
                VehicleInspectionFormScreenEvent.InitialEvent(
                    committeeViewModel.committeeScreenState.students
                )
            )

            VehicleInspectionFormScreen(viewModel.vehicleInspectionFormScreenState, { event ->
                when (event) {
                    VehicleInspectionFormScreenEvent.OnCancelClicked -> navController.navigateUp()
                    VehicleInspectionFormScreenEvent.OnSendClicked -> navController.navigate(
                        CommitteeScreenRoute.RouteSelectionRoute
                    )

                    else -> viewModel.onEventReceived(event)
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

            snapshotFlow { viewModel.studentListScreenState.students }
                .onEach {
                    committeeViewModel.onEventReceived(CommitteeScreenEvent.OnStudentsFetched(it))
                }.launchIn(viewModel.viewModelScope)

            StudentListScreen(viewModel.studentListScreenState, { event ->
                when (event) {
                    StudentListScreenEvent.OnNextClicked -> {
                        navController.navigate(CommitteeScreenRoute.VehicleInspectionFormRoute)
                    }

                    else -> viewModel.onEventReceived(event)
                }
            })
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
        CommitteeScreen()
    }
}