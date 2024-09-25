package com.tatweer.smartdrivingtest.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tatweer.smartdrivingtest.R


private val DarkColorScheme = darkColorScheme(
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = AppColors.Primary,
    secondary = AppColors.Secondary,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)


val QuarterDefaultDp = 4.dp
val HalfDefaultDp = 8.dp
val DefaultDp = 16.dp
val ThreeQuarteredDoubleDefaultDp = 24.dp
val DoubleDefaultDp = 32.dp

val QuarterDefaultSp = 4.sp
val HalfDefaultSp = 8.sp
val DefaultSp = 16.sp
val ThreeQuarteredDoubleDefaultSp = 24.sp
val DoubleDefaultSp = 32.sp

// Colors
object AppColors {
    val Primary = Color(0xFF7C2636)
    val Secondary = Color(0xFFB8906D)
    val Gray = Color(0xFFC9C9C9)
    val Lotion = Color(0xFFFCFCFC)
    val LightRed = Color(0xFFD54F54)
}

// Font
object AppFont {
    val PoppinsFont = FontFamily(
        Font(R.font.poppins),
        Font(R.font.poppins_semibold, weight = FontWeight.SemiBold),
    )
}


@Composable
fun SmartDrivingTestExaminerNewTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}