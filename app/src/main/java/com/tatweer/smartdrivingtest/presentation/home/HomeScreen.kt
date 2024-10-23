package com.tatweer.smartdrivingtest.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.home.navhost.HomeNavHost
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.utils.ConsumeEach
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onLogoutClicked: () -> Unit) {

    val viewModel: HomeViewModel = koinViewModel()
    Box(modifier.padding(DefaultDp)) {
        Row {
            val navController = rememberNavController()
            viewModel.singleStateEventChannel.ConsumeEach {
                when (it) {
                    HomeScreenStateEvent.PopBackToStudentList -> {
                        navController.popBackStack(
                            HomeNavigationDestinations.Committee,
                            inclusive = false
                        )
                    }
                }
            }

            val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
            var currentDestination = remember(currentNavBackStackEntry) {
                HomeNavigationDestinations.fromRoute(
                    currentNavBackStackEntry?.destination?.route ?: ""
                ) ?: HomeNavigationDestinations.Committee
            }
            HomeNavigationRail(
                currentDestination,
                onDestinationSelected = {
                    currentDestination = it
                    navController.navigate(it) {
                        popUpTo(navController.graph.startDestinationRoute!!) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onLogoutClicked = onLogoutClicked,
                viewModel.homeScreenState.startedStudent != null,
                Modifier
                    .width(120.dp)
            )
            Column(
                Modifier
                    .padding(start = DefaultDp)
            ) {
                Row(
                    Modifier
                        .height(IntrinsicSize.Min)
                ) {
                    Spacer(Modifier.weight(1f))
                    Image(
                        painterResource(R.drawable.logo_smart_driving_test_red),
                        contentDescription = null
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxSize(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    HomeNavHost(
                        navController = navController,
                        state = viewModel.homeScreenState,
                        viewModel::onEvent
                    )
                }
            }
        }
    }
}

@PreviewTablet
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(onLogoutClicked = {})
    }
}