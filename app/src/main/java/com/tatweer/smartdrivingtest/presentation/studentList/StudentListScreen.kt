package com.tatweer.smartdrivingtest.presentation.studentList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp

@Composable
fun StudentListScreen(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(DefaultDp),
        verticalArrangement = Arrangement.spacedBy(DefaultDp),
        horizontalArrangement = Arrangement.spacedBy(DefaultDp),
    ) {
        items(10) {
            StudentCard()
        }
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun StudentListScreenPreview() {
    StudentListScreen()
}