package com.tatweer.smartdrivingtest.presentation.systemHealth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tatweer.smartdrivingtest.domain.model.SystemHealth
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import kotlin.random.Random

@Composable
fun SystemHealthScreen(
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        GridCells.Fixed(4),
        modifier.fillMaxSize(),
        contentPadding = PaddingValues(DefaultDp),
        verticalArrangement = Arrangement.spacedBy(DefaultDp),
        horizontalArrangement = Arrangement.spacedBy(DefaultDp)
    ) {
        items(10) {
            SystemHealthItem(
                SystemHealth(
                    id = 9553,
                    title = "Gear is not working",
                    isWorking = Random.nextBoolean()
                ),
                Modifier.fillMaxSize(),
            )
        }
    }
}

@PreviewTablet
@Composable
private fun PreviewSystemHealthScreen() {
    AppTheme {
        SystemHealthScreen()
    }
}