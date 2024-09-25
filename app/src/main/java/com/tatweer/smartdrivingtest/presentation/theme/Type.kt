package com.tatweer.smartdrivingtest.presentation.theme

import androidx.compose.material3.Typography

private val defaultTypography = Typography()

val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = AppFont.PoppinsFont),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = AppFont.PoppinsFont),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = AppFont.PoppinsFont),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = AppFont.PoppinsFont),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = AppFont.PoppinsFont),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = AppFont.PoppinsFont),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = AppFont.PoppinsFont),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = AppFont.PoppinsFont),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = AppFont.PoppinsFont),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = AppFont.PoppinsFont),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = AppFont.PoppinsFont),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = AppFont.PoppinsFont),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = AppFont.PoppinsFont),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = AppFont.PoppinsFont),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = AppFont.PoppinsFont)
)
