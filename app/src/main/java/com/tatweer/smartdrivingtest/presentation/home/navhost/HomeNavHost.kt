package com.tatweer.smartdrivingtest.presentation.home.navhost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tatweer.smartdrivingtest.presentation.driveTest.DriveTestScreen
import com.tatweer.smartdrivingtest.presentation.studentList.StudentListScreen
import com.tatweer.smartdrivingtest.presentation.home.HomeNavigationDestinations

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

        composable<HomeNavigationDestinations.StudentList> {
            StudentListScreen()
        }
    }
}

@Preview
@Composable
private fun HomeNavHostPreview() {
    HomeNavHost()
}