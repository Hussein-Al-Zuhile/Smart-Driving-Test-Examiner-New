package com.tatweer.smartdrivingtest.presentation.main.navhost

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tatweer.smartdrivingtest.presentation.home.HomeScreen
import com.tatweer.smartdrivingtest.presentation.login.LoginScreen
import com.tatweer.smartdrivingtest.presentation.login.LoginScreenStateEvent
import com.tatweer.smartdrivingtest.presentation.login.LoginViewModel
import com.tatweer.smartdrivingtest.presentation.splash.SplashScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    NavHost(navController = navController, startDestination = MainDestinations.Splash, modifier) {
        composable<MainDestinations.Splash> {
            SplashScreen(onPermissionGranted = {
                navController.navigate(route = MainDestinations.Login, builder = {
                    popUpTo(MainDestinations.Splash) {
                        inclusive = true
                    }
                })
            })
        }

        composable<MainDestinations.Login> {
            val viewModel: LoginViewModel = koinViewModel()
            LaunchedEffect(viewModel.singleStateEventChannel) {
                for (event in viewModel.singleStateEventChannel) {
                    when (event) {
                        is LoginScreenStateEvent.OnLoginSucceeded -> {
                            navController.navigate(route = MainDestinations.Home, builder = {
                                popUpTo(MainDestinations.Login) {
                                    inclusive = true
                                }
                            })
                        }
                    }
                }
            }
            LoginScreen(viewModel.loginScreenState, viewModel::onEvent)
        }

        composable<MainDestinations.Home> {
            CompositionLocalProvider(LocalHomeBackstackEntry provides it) {
                HomeScreen(onLogoutClicked = {
                    navController.navigate(MainDestinations.Login) {
                        popUpTo(MainDestinations.Home) {
                            inclusive = true
                        }
                    }
                })
            }
        }
    }

}

val LocalHomeBackstackEntry = compositionLocalOf<NavBackStackEntry?> { null }

sealed interface MainDestinations {
    @Serializable
    object Splash

    @Serializable
    object Login

    @Serializable
    object Home
}
