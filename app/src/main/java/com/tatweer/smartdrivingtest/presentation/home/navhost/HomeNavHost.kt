package com.tatweer.smartdrivingtest.presentation.home.navhost

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tatweer.smartdrivingtest.presentation.driveTest.DriveTestScreen
import com.tatweer.smartdrivingtest.presentation.committee.CommitteeScreen
import com.tatweer.smartdrivingtest.presentation.home.HomeNavigationDestinations
import com.tatweer.smartdrivingtest.presentation.home.HomeScreenEvent
import com.tatweer.smartdrivingtest.presentation.home.HomeScreenState
import com.tatweer.smartdrivingtest.presentation.main.LocalAnimatedContentScope
import com.tatweer.smartdrivingtest.presentation.systemHealth.SystemHealthScreen

@Composable
fun HomeNavHost(
    navController: NavHostController,
    state: HomeScreenState,
    onEvent: (HomeScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeNavigationDestinations.Committee,
        modifier = modifier
    ) {
        composable<HomeNavigationDestinations.DriveTest> {
            DriveTestScreen(state, onEvent)
        }

        composable<HomeNavigationDestinations.Committee> {
            CommitteeScreen(onEvent)
        }
        composable<HomeNavigationDestinations.SystemHealth> {
            SystemHealthScreen()
        }
    }
}

@Preview
@Composable
private fun HomeNavHostPreview() {
    HomeNavHost(rememberNavController(), HomeScreenState(), {})
}