package com.tatweer.smartdrivingtest.presentation.systemHealth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarCrash
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.tatweer.smartdrivingtest.domain.model.SystemHealth
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp

@Composable
fun SystemHealthItem(
    systemHealth: SystemHealth,
    modifier: Modifier = Modifier
) {
    OutlinedCard(modifier.fillMaxSize()) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(DefaultDp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.CarCrash, contentDescription = null,
                Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(DefaultDp),
            )
            Spacer(Modifier.width(HalfDefaultDp))
            Text(text = systemHealth.title, Modifier.weight(1f))
            Spacer(Modifier.width(HalfDefaultDp))
            Box(
                Modifier
                    .fillMaxWidth(0.1f)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .background(if (systemHealth.isWorking) Color.Green else Color.Red)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSystemHealthItem() {
    AppTheme {
        SystemHealthItem(SystemHealth(1, "title", true), Modifier.fillMaxSize())
    }
}