package com.tatweer.smartdrivingtest.presentation.base

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview


@Preview(device = Devices.TABLET, showBackground = true)
@Preview(device = Devices.TABLET, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
annotation class PreviewTablet

@Preview(name = "85%", fontScale = 0.85f, device = Devices.TABLET)
@Preview(name = "100%", fontScale = 1.0f, device = Devices.TABLET)
@Preview(name = "115%", fontScale = 1.15f, device = Devices.TABLET)
@Preview(name = "130%", fontScale = 1.3f, device = Devices.TABLET)
@Preview(name = "150%", fontScale = 1.5f, device = Devices.TABLET)
@Preview(name = "180%", fontScale = 1.8f, device = Devices.TABLET)
@Preview(name = "200%", fontScale = 2f, device = Devices.TABLET)
annotation class PreviewTablets
