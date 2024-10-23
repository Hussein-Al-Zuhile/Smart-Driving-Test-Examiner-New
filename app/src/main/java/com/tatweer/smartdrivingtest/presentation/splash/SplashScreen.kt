package com.tatweer.smartdrivingtest.presentation.splash

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Copyright
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.presentation.theme.DoubleDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import kotlinx.coroutines.delay


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SplashScreen(
    onPermissionGranted: () -> Unit,
    modifier: Modifier = Modifier
) {

    val permissionRequest = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
        )
    )
    LaunchedEffect(permissionRequest.allPermissionsGranted) {
        if (!permissionRequest.allPermissionsGranted) {
            permissionRequest.launchMultiplePermissionRequest()
        } else {
            delay(1000)
            onPermissionGranted()
        }
    }

    SplashScreenUI(modifier)
}

@Composable
private fun SplashScreenUI(modifier: Modifier = Modifier) {
    Image(
        painterResource(R.drawable.bg_splash_screen),
        contentDescription = null,
        modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun Loader() {
    val infiniteTransition = rememberInfiniteTransition(label = "Loader Infinite Transition")
    val colorsOffset by infiniteTransition.animateFloat(
        0f, 1f, label = "Colors Offset",
        animationSpec = infiniteRepeatable(
            tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        Modifier.Companion
            .width(50.dp)
            .padding(bottom = DoubleDefaultDp)
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(
                Brush.sweepGradient(
                    colorStops = arrayOf(
                        0f to Color.Transparent,
                        colorsOffset to Color.White,
                        1f to Color.Transparent,
                    )
                )
            )
    )
}

@Preview(device = Devices.TABLET)
@Composable
private fun SplashScreenPreview() {
    AppTheme {
        SplashScreenUI()
    }
}