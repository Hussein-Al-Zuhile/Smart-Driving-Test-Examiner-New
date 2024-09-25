package com.tatweer.smartdrivingtest.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.presentation.home.navhost.HomeNavHost
import com.tatweer.smartdrivingtest.presentation.theme.AppColors.Lotion
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.SmartDrivingTestExaminerNewTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Surface(modifier) {
        Row() {
            val navController = rememberNavController()
            val currentDestination =
                navController.currentBackStackEntryAsState().value?.toRoute<HomeNavigationDestinations>()
                    ?: HomeNavigationDestinations.DriveTest
            HomeNavigationRail(
                currentDestination,
                { navController.navigate(it) },
                onLogoutClicked = {},
                Modifier.width(150.dp)
            )
            Column {
                Row(
                    Modifier
                        .height(IntrinsicSize.Min)
                        .padding(DefaultDp)
                ) {
                    var searchBarExpanded by remember { mutableStateOf(false) }
                    var query by remember { mutableStateOf("") }
                    SearchBar(
                        inputField = {
                            SearchBarDefaults.InputField(
                                query,
                                onQueryChange = { query = it },
                                onSearch = {},
                                expanded = searchBarExpanded,
                                onExpandedChange = { searchBarExpanded = it }
                            )
                        },
                        expanded = searchBarExpanded,
                        onExpandedChange = {
                            searchBarExpanded = it
                        },
                        Modifier.weight(1f)
                    ) { }

                    Spacer(Modifier.weight(1f))
                    Image(
                        painterResource(R.drawable.logo_smart_driving_test_red),
                        contentDescription = null,
                        Modifier.weight(1f),
                    )
                }

                ElevatedCard(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(DefaultDp),
                    colors = CardDefaults.cardColors(containerColor = Lotion),
                ) {
                    HomeNavHost(navController = navController)
                }
            }
        }
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun HomeScreenPreview() {
    SmartDrivingTestExaminerNewTheme {
        HomeScreen()
    }
}