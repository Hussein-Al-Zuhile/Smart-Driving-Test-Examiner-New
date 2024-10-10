package com.tatweer.smartdrivingtest.presentation.home.navhost

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tatweer.smartdrivingtest.domain.model.Student
import com.tatweer.smartdrivingtest.presentation.committee.CommitteeViewModel
import com.tatweer.smartdrivingtest.presentation.driveTest.DriveTestScreen
import com.tatweer.smartdrivingtest.presentation.committee.CommitteeScreen
import com.tatweer.smartdrivingtest.presentation.committee.CommitteeScreenState
import com.tatweer.smartdrivingtest.presentation.home.HomeNavigationDestinations
import kotlinx.coroutines.flow.flow
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = HomeNavigationDestinations.DriveTest,
        modifier = modifier
    ) {
        composable<HomeNavigationDestinations.DriveTest> {
            DriveTestScreen()
        }

        composable<HomeNavigationDestinations.Committee> {
            // TODO: Move the using of viewmodel here if available
            val committeeViewModel = koinViewModel<CommitteeViewModel>()
            CommitteeScreen()
        }
    }
}

@Preview
@Composable
private fun HomeNavHostPreview() {
    HomeNavHost()
}